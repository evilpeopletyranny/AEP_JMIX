package ru.ase.xls;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ase.entity.tag.classifier.TagClassifier;
import ru.ase.entity.tag.classifier.TagClassifierFactory;

import java.util.*;

import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link TagClassifierXlsxParser}.
 * <p>
 * This test class verifies the functionality of the TagClassifierXlsxParser by testing:
 * <ul>
 *   <li>Sheet creation with headers and data rows using lists instead of arrays.</li>
 *   <li>Handling of empty sheets.</li>
 *   <li>Handling of missing required header columns.</li>
 *   <li>Correct parsing of a valid hierarchy of TagClassifier entities.</li>
 *   <li>Proper parent-child linking between entities.</li>
 * </ul>
 * </p>
 */
class TagClassifierXlsxParserUnitTest {
    private TagClassifierFactory tagClassifierFactory;

    private TagClassifierXlsxParser parser;

    /**
     * Sets up the test environment by creating a mock DataManager and initializing the parser.
     */
    @BeforeEach
    void setUp() {
        tagClassifierFactory = mock(TagClassifierFactory.class);
        when(tagClassifierFactory.createTagClassifier(anyString(), any())).thenAnswer(invocation -> {
            String name = invocation.getArgument(0);
            TagClassifier parent = invocation.getArgument(1);
            TagClassifier tc = new TagClassifier();
            tc.setId(UUID.randomUUID());
            tc.setName(name);
            tc.setParentClassifier(parent);
            return tc;
        });
        parser = new TagClassifierXlsxParser(tagClassifierFactory);
    }

    /**
     * Creates a test Excel sheet with the given headers and data rows.
     *
     * @param workbook the workbook in which the sheet is created
     * @param headers  a list of header names for the first row
     * @param dataRows a list of data rows, where each row is represented as a list of cell values
     * @return the created Sheet instance
     */
    private Sheet createTestSheet(Workbook workbook, List<String> headers, List<List<String>> dataRows) {
        Sheet sheet = workbook.createSheet("Test");
        // Create header row
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers.get(i));
        }
        // Create data rows
        for (int r = 0; r < dataRows.size(); r++) {
            Row row = sheet.createRow(r + 1);
            List<String> rowData = dataRows.get(r);
            for (int c = 0; c < rowData.size(); c++) {
                Cell cell = row.createCell(c);
                cell.setCellValue(rowData.get(c));
            }
        }
        return sheet;
    }

    /**
     * Tests that if the required header columns are missing, an {@link IllegalArgumentException} is thrown.
     */
    @Test
    void testMissingRequiredHeaders() {
        Workbook workbook = new XSSFWorkbook();
        // Create a sheet with only the "Наименование" header, missing "Parent Classification"
        List<String> headers = List.of("Наименование");
        List<List<String>> dataRows = List.of(
                List.of("Код1")
        );

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                parser.handleSheet(
                        createTestSheet(workbook, headers, dataRows),
                        "Наименование",
                        "Parent Classification"
                )
        );
    }

    /**
     * Tests that a valid hierarchy is correctly parsed from the sheet.
     * <p>
     * The sheet structure is as follows:
     * <ul>
     *   <li>Row 1: Headers ("Наименование", "Parent Classification")</li>
     *   <li>Row 2: "A", "" (root element)</li>
     *   <li>Row 3: "B", "A" (child of A)</li>
     *   <li>Row 4: "C", "A" (child of A)</li>
     * </ul>
     * </p>
     */
    @Test
    void testValidHierarchy() {
        Workbook workbook = new XSSFWorkbook();
        List<String> headers = List.of("Наименование", "Parent Classification");
        // Data rows: "A" without a parent, "B" and "C" with parent "A"
        List<List<String>> dataRows = List.of(
                List.of("A", ""),
                List.of("B", "A"),
                List.of("C", "A")
        );
        Sheet sheet = createTestSheet(workbook, headers, dataRows);

        // Use the parser's handleSheet method to parse the hierarchy.
        Collection<TagClassifier> classifiers = parser.handleSheet(sheet,
                "Наименование",
                "Parent Classification");

        // Assert that three TagClassifier objects are created
        assertEquals(3, classifiers.size(), "There should be 3 classifiers created");

        // Verify that the TagClassifierFactory's createTagClassifier method was called exactly 3 times.
        verify(tagClassifierFactory, times(3)).createTagClassifier(anyString(), any());
    }

    /**
     * Test to verify that the parent-child linking between TagClassifier entities is set up correctly.
     * <p>
     * The sheet structure is as follows:
     * <ul>
     *   <li>Row 1: Headers ("Наименование", "Parent Classification")</li>
     *   <li>Row 2: "Root", "" (root element)</li>
     *   <li>Row 3: "Child", "Root" (child of Root)</li>
     * </ul>
     * </p>
     */
    @Test
    void testParentChildLinking() {
        Workbook workbook = new XSSFWorkbook();
        List<String> headers = List.of("Наименование", "Parent Classification");
        List<List<String>> dataRows = List.of(
                List.of("Root", ""),
                List.of("Child", "Root")
        );
        Sheet sheet = createTestSheet(workbook, headers, dataRows);

        Collection<TagClassifier> classifiers = parser.handleSheet(sheet, "Наименование", "Parent Classification");
        TagClassifier child = classifiers.stream().filter(tc -> "Child".equals(tc.getName())).findFirst().orElse(null);
        TagClassifier root = classifiers.stream().filter(tc -> "Root".equals(tc.getName())).findFirst().orElse(null);

        Assertions.assertNotNull(child);
        Assertions.assertNotNull(root);
        Assertions.assertEquals(root.getId(), child.getParentClassifier().getId());
    }

    /**
     * Test to verify that the order of TagClassifier entities returned by handleSheet
     * reflects the hierarchy – parent's appear before their children.
     * <p>
     * The sheet structure is as follows:
     * <ul>
     *   <li>Row 1: Headers ("Наименование", "Parent Classification")</li>
     *   <li>Row 2: "Root", "" (root element)</li>
     *   <li>Row 3: "Child1", "Root" (child of Root)</li>
     *   <li>Row 4: "Child2", "Root" (child of Root)</li>
     *   <li>Row 5: "GrandChild", "Child1" (child of Child1)</li>
     * </ul>
     * The test checks that for each classifier with a non-null parent, the parent's position in the list
     * comes before the child's position.
     * </p>
     */
    @Test
    void testHierarchyOrdering() {
        Workbook workbook = new XSSFWorkbook();
        List<String> headers = List.of("Наименование", "Parent Classification");
        List<List<String>> dataRows = List.of(
                List.of("Root", ""),         // Row 2
                List.of("Child1", "Root"),    // Row 3
                List.of("Child2", "Root"),    // Row 4
                List.of("GrandChild", "Child1") // Row 5
        );

        Sheet sheet = createTestSheet(workbook, headers, dataRows);

        List<TagClassifier> classifiers = parser.handleSheet(sheet, "Наименование", "Parent Classification")
                .stream().toList();

        Map<String, Integer> orderMap = new HashMap<>();
        for (int i = 0; i < classifiers.size(); i++) {
            orderMap.put(classifiers.get(i).getName(), i);
        }

        // For each classifier with a parent, verify that parent's index is less than child's index.
        for (TagClassifier classifier : classifiers) {
            TagClassifier parent = classifier.getParentClassifier();
            if (parent != null) {
                Integer parentIndex = orderMap.get(parent.getName());
                Integer childIndex = orderMap.get(classifier.getName());
                assertNotNull(parentIndex, "Parent classifier '" + parent.getName() + "' should be present in the order map.");
                assertNotNull(childIndex, "Child classifier '" + classifier.getName() + "' should be present in the order map.");
                assertTrue(parentIndex < childIndex,
                        "Parent '" + parent.getName() + "' should come before child '" + classifier.getName() + "'.");
            }
        }
    }
}
