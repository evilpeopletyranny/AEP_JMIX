package ru.ase.xls;

import io.jmix.core.DataManager;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import ru.ase.entity.tag.classifier.TagClassifier;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;

@Component
public class TagClassifierXlsxImportService {
    private final TagClassifierXlsxParser tagClassifierXlsxParser;
    private final DataManager dataManager;

    public TagClassifierXlsxImportService(TagClassifierXlsxParser tagClassifierXlsxParser, DataManager dataManager) {
        this.tagClassifierXlsxParser = tagClassifierXlsxParser;
        this.dataManager = dataManager;
    }

    public void processFile(File file) {
        try (XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file))) {
            Collection<TagClassifier> classifiers = tagClassifierXlsxParser.handleSheet(workbook.getSheetAt(0),
                    "Наименование",
                    "Parent Classification");

            dataManager.saveAll(classifiers);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}