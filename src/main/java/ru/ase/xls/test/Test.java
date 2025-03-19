package ru.ase.xls.test;

import io.jmix.core.DataManager;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;
import ru.ase.AEPJMIXApplication;
import ru.ase.entity.tag.classifier.TagClassifier;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class Test {

    private static final Logger log = LoggerFactory.getLogger(Test.class);

    private void createHierarchy(Sheet sheet, DataManager dataManager) {
        Row headerRow = sheet.getRow(0);
        Map<String, Integer> headerMap = new HashMap<>();
        for (Cell cell : headerRow) headerMap.put(cell.getStringCellValue(), cell.getColumnIndex());

        Iterator<Row> rowIterator = sheet.rowIterator();
        if (rowIterator.hasNext()) rowIterator.next();

        Map<String, TagClassifier> classifierMap = new HashMap<>();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            var name = row.getCell(headerMap.get("Наименование")).getStringCellValue();
            var parentName = row.getCell(headerMap.get("Parent Classification")).getStringCellValue();
            if (!classifierMap.containsKey(name)) {
                var entity = dataManager.create(TagClassifier.class);
                entity.setId(UUID.randomUUID());
                entity.setParentClassifier(classifierMap.getOrDefault(parentName, null));
                entity.setName(name);

                classifierMap.put(name, entity);
                dataManager.save(entity);
            }
        }
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(AEPJMIXApplication.class)
                .web(WebApplicationType.NONE) // не поднимаем веб-сервер
                .run(args);

        DataManager dataManager = context.getBean(DataManager.class);

        try (InputStream is = new FileInputStream("src/main/resources/ru/ase/xls/Classification.xlsx");
             Workbook workbook = WorkbookFactory.create(is)) {
            Sheet sheet = workbook.getSheetAt(0);
            new Test().createHierarchy(sheet, dataManager);

        } catch (
                IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static String getCellValue(Cell cell) {
        String value;
        switch (cell.getCellType()) {
            case STRING:
                value = cell.getStringCellValue();
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    Date date = cell.getDateCellValue();
                    value = date.toString();
                } else {
                    value = Double.toString(cell.getNumericCellValue());
                }
                break;
            case BOOLEAN:
                value = Boolean.toString(cell.getBooleanCellValue());
                break;
            default:
                value = "";
        }
        return value;
    }
}

//        try (InputStream is = new FileInputStream("src/main/resources/ru/ase/xls/Classification.XLSB");
//Workbook workbook = WorkbookFactory.create(is)) {
//
//// Читаем первый лист из книги
//Sheet sheet = workbook.getSheetAt(0);
//
//// Проходим по всем строкам и ячейкам
//            for (Row row : sheet) {
//        for (Cell cell : row) {
//        // Вывод значения ячейки (можно доработать для разных типов)
//        System.out.print(cell.toString() + "\t");
//        }
//        System.out.println();
//            }
//                    } catch (IOException e) {
//        e.printStackTrace();
//        }
