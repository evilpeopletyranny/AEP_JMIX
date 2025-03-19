package ru.ase.xls;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Component;
import ru.ase.entity.tag.classifier.TagClassifier;
import ru.ase.entity.tag.classifier.TagClassifierFactory;

import java.util.*;

/**
 * Service for parsing XLSX files containing TagClassifier hierarchy data.
 * <p>
 * This class reads an XLSX workbook and extracts the TagClassifier hierarchy based on the provided
 * column titles for the classifier name and its parent. The hierarchy is built using a {@link TagClassifierFactory}.
 * The resulting entities can then be processed or saved accordingly.
 * </p>
 */
@Component
public class TagClassifierXlsxParser {
    private final TagClassifierFactory tagClassifierFactory;

    /**
     * Constructs a new TagClassifierXlsxParser with the given TagClassifierFactory.
     *
     * @param tagClassifierFactory the factory used to create TagClassifier entities.
     */
    public TagClassifierXlsxParser(TagClassifierFactory tagClassifierFactory) {
        this.tagClassifierFactory = tagClassifierFactory;
    }

    /**
     * Reads the header row from the given cell iterator and returns a mapping of header names
     * to their corresponding column indexes.
     *
     * @param cellIterator the iterator over header cells.
     * @return a map where keys are trimmed header names and values are the corresponding column indexes.
     */
    private Map<String, Integer> readHeaders(Iterator<Cell> cellIterator) {
        Map<String, Integer> headerMap = new HashMap<>();
        while (cellIterator.hasNext()) {
            var cell = cellIterator.next();
            headerMap.put(cell.getStringCellValue().trim(), cell.getColumnIndex());
        }
        return headerMap;
    }

    /**
     * Reads and validates the header row from the given cell iterator.
     *
     * @param cellIterator      the iterator over header cells.
     * @param nameColumnTitle   the expected header for the classifier name column.
     * @param parentColumnTitle the expected header for the parent classifier column.
     * @return a map of header names to column indexes after validation.
     * @throws IllegalArgumentException if any of the required headers are missing.
     */
    private Map<String, Integer> readAndValidateHeaders(Iterator<Cell> cellIterator,
                                                        String nameColumnTitle,
                                                        String parentColumnTitle) {
        Map<String, Integer> headerMap = readHeaders(cellIterator);
        validateRequiredHeaders(headerMap, nameColumnTitle, parentColumnTitle);
        return headerMap;
    }

    /**
     * Validates that the required header columns are present in the given header map.
     *
     * @param headerMap         a map of header names to column indexes.
     * @param nameColumnTitle   the expected header for the name column.
     * @param parentColumnTitle the expected header for the parent column.
     * @throws IllegalArgumentException if any of the required headers is missing.
     */
    private void validateRequiredHeaders(Map<String, Integer> headerMap, String nameColumnTitle, String parentColumnTitle) {
        if (!headerMap.containsKey(nameColumnTitle) || !headerMap.containsKey(parentColumnTitle)) {
            throw new IllegalArgumentException("В заголовках отсутствуют обязательные столбцы: "
                    + nameColumnTitle + " или " + parentColumnTitle);
        }
    }

    /**
     * Reads the TagClassifier hierarchy from the provided Excel sheet based on the given column titles.
     * <p>
     * The method first validates that the sheet is not empty and then reads the header row to determine
     * the indexes for the classifier name and parent classifier columns. It processes the remaining rows
     * to build a hierarchy of TagClassifier entities. The resulting collection is sorted by hierarchy level.
     * </p>
     *
     * @param sheet             the Excel sheet containing the data.
     * @param nameColumnTitle   the title of the column that contains classifier names.
     * @param parentColumnTitle the title of the column that contains parent classifier names.
     * @return a sorted collection of TagClassifier entities representing the hierarchy.
     * @throws IllegalStateException    if the sheet is empty.
     * @throws IllegalArgumentException if the required header columns are missing.
     */
    public Collection<TagClassifier> handleSheet(Sheet sheet, String nameColumnTitle, String parentColumnTitle) {
        validateSheetNotEmpty(sheet);
        Iterator<Row> rowIterator = sheet.rowIterator();

        Map<String, Integer> headerMap = readAndValidateHeaders(rowIterator.next().cellIterator(),
                nameColumnTitle,
                parentColumnTitle);

        return readHierarchy(rowIterator, headerMap.get(nameColumnTitle), headerMap.get(parentColumnTitle)).stream()
                .sorted(Comparator.comparing(this::getHierarchyLevel))
                .toList();
    }


    /**
     * Processes the data rows from the given row iterator to build a hierarchy of TagClassifier entities.
     *
     * @param rowIterator the iterator over the data rows.
     * @param nameColInd  the column index for the classifier name.
     * @param parentColInd the column index for the parent classifier name.
     * @return a collection of TagClassifier entities built from the sheet data.
     */
    private Collection<TagClassifier> readHierarchy(Iterator<Row> rowIterator,
                                                    Integer nameColInd,
                                                    Integer parentColInd) {
        Map<String, TagClassifier> classifierMap = new HashMap<>();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            String name = getCellValue(row.getCell(nameColInd, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK));

            if (name.isEmpty()) continue;

            String parentName = getCellValue(row.getCell(parentColInd, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK));

            if (!classifierMap.containsKey(name))
                classifierMap.put(name, tagClassifierFactory.createTagClassifier(name, classifierMap.get(parentName)));
        }
        return classifierMap.values();
    }

    /**
     * Validates that the provided Excel sheet is not empty.
     *
     * @param sheet the Excel sheet to validate.
     * @throws IllegalStateException if the sheet has no rows.
     */
    private void validateSheetNotEmpty(Sheet sheet) {
        if (!sheet.rowIterator().hasNext()) {
            throw new IllegalStateException("Лист пуст, отсутствует строка заголовков.");
        }
    }



    /**
     * Retrieves the string value from the given cell, trimmed of whitespace.
     *
     * @param cell the cell to extract the value from
     * @return the trimmed string value of the cell
     */
    private String getCellValue(Cell cell) {
        return cell.getStringCellValue().trim();
    }

    /**
     * Calculates the hierarchy level of the given TagClassifier.
     * <p>
     * The root level is 0. Each level deeper in the hierarchy increments the level by 1.
     * </p>
     *
     * @param classifier the TagClassifier entity to calculate the level for.
     * @return the hierarchy level as an Integer.
     */
    public Integer getHierarchyLevel(TagClassifier classifier) {
        Integer level = 0;
        TagClassifier current = classifier;
        while (current.getParentClassifier() != null) {
            level++;
            current = current.getParentClassifier();
        }
        return level;
    }
}