package ru.ase.xls;

import io.jmix.core.DataManager;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import ru.ase.entity.tag.classifier.TagClassifier;
import ru.ase.entity.tag.classifier.TagClassifierFactory;
import ru.ase.xls.tag.classifier.TagClassifierXlsxParser;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Integration tests for {@link TagClassifierXlsxParser} using the real XLSX file "TestClassification.xlsx".
 * <p>
 * This test class verifies that the parser correctly reads and parses the XLSX file located at
 * "src/test/resources/ru/ase/xls/TestClassification.xlsx", builds the correct TagClassifier hierarchy,
 * and sets up proper parent-child relationships. The tests also check that the hierarchy ordering is correct,
 * which is crucial for proper persistence order in the database.
 * </p>
 * <p>
 * The workbook is opened once before all tests and closed after all tests have run.
 * A mocked {@link DataManager} is used to simulate creation of {@link TagClassifier} objects via a
 * real instance of {@link TagClassifierFactory}.
 * </p>
 */
public class TagClassifierXlsxParserIntegrationTest {

    private static Workbook workbook;
    private TagClassifierXlsxParser parser;


    /**
     * Sets up the parser before each test.
     * <p>
     * A mocked {@link DataManager} is created and configured so that when its create method is called,
     * a new {@link TagClassifier} is returned with a generated UUID. The parser is then initialized
     * using a real instance of {@link TagClassifierFactory} with the mocked DataManager.
     * </p>
     */
    @BeforeEach
    void setUpParser() {
        DataManager dataManager = mock(DataManager.class);
        // Когда dataManager.create(TagClassifier.class) вызывается, возвращаем новый объект TagClassifier.
        when(dataManager.create(TagClassifier.class)).thenAnswer(invocation -> {
            TagClassifier tc = new TagClassifier();
            tc.setId(UUID.randomUUID());
            return tc;
        });
        parser = new TagClassifierXlsxParser(new TagClassifierFactory(dataManager));
    }

    /**
     * Opens the workbook "TestClassification.xlsx" from test resources.
     * <p>
     * This method is executed once before all tests. It loads the XLSX file from the specified location.
     * </p>
     */
    @BeforeAll
    static void openWorkbook() {
        try {
            workbook = new XSSFWorkbook(new FileInputStream("src/test/resources/ru/ase/xls/TestClassification.xlsx"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Closes the workbook after all tests have run.
     * <p>
     * This method is executed once after all tests to ensure that the workbook resource is properly released.
     * </p>
     */
    @AfterAll
    static void closeWorkbook() {
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests that the parser correctly reads and parses the rows from the file.
     * <p>
     * This test verifies that the collection of TagClassifier entities parsed from the file is not null,
     * not empty, and contains the expected number of entities.
     * </p>
     */
    @Test
    void testParsingRowsFromFile() {
        Sheet sheet = workbook.getSheetAt(0);

        // Parse the hierarchy from the sheet.
        Collection<TagClassifier> classifiers = parser.handleSheet(sheet, "Наименование", "Parent Classification");

        Assertions.assertNotNull(classifiers, "The classifiers collection should not be null");
        Assertions.assertFalse(classifiers.isEmpty(), "The classifiers collection should not be empty");

        Assertions.assertEquals(4, classifiers.size(), "Unexpected number of classifiers parsed from the file");
    }

    /**
     * Tests that the hierarchy levels are set correctly.
     * <p>
     * For each TagClassifier with a non-null parent, the test verifies that the parent's name is not null or empty,
     * and that the parent's hierarchy level is less than the child's hierarchy level.
     * </p>
     */
    @Test
    void testHierarchyLevels() {
        Sheet sheet = workbook.getSheetAt(0);
        Collection<TagClassifier> classifiers = parser.handleSheet(sheet, "Наименование", "Parent Classification");

        // Verify parent-child relationships:
        // For each classifier with a parent, ensure that the parent's name is not empty.
        classifiers.forEach(tc -> {
            if (tc.getParentClassifier() != null) {
                Assertions.assertNotNull(tc.getParentClassifier().getName(), "Parent classifier's name should not be null");
                Assertions.assertFalse(tc.getParentClassifier().getName().isEmpty(), "Parent classifier's name should not be empty");
            }
        });

        // Verify that the order of entities (sorted by hierarchy level) is correct:
        // Any parent's hierarchy level should be less than its child's.
        for (TagClassifier tc : classifiers) {
            if (tc.getParentClassifier() != null) {
                int parentLevel = parser.getHierarchyLevel(tc.getParentClassifier());
                int childLevel = parser.getHierarchyLevel(tc);
                Assertions.assertTrue(parentLevel < childLevel,
                        "Parent classifier must appear before child classifier in hierarchy order");
            }
        }
    }

    /**
     * Tests that the hierarchy structure matches the expected relationships.
     * <p>
     * This test builds an expected mapping of TagClassifier names to their parent names and then compares it with
     * the mapping obtained from parsing the XLSX file.
     * </p>
     */
    @Test
    void testHierarchyHierarchy() {
        Map<String, String> testDataMap = new HashMap<>();
        testDataMap.put("Классификатор ПП", null);
        testDataMap.put("Агрегат", "Классификатор ПП");
        testDataMap.put("Арматура технологическая", "Агрегат");
        testDataMap.put("Арматура воздушная", "Арматура технологическая");

        Map<String, String> dataMap = new HashMap<>();
        Sheet sheet = workbook.getSheetAt(0);
        parser.handleSheet(sheet, "Наименование", "Parent Classification")
                .forEach(classifier -> dataMap.put(classifier.getName(), classifier.getParentClassifier() == null ? null : classifier.getParentClassifier().getName()));

        Assertions.assertEquals(testDataMap, dataMap);
    }
}
