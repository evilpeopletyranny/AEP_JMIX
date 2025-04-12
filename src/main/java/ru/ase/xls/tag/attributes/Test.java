package ru.ase.xls.tag.attributes;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.ase.entity.tag.group.TagAttributeGroup;
import ru.ase.xls.utils.XLSParseUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Test {
    public static void main(String[] args) throws IOException {
        Workbook workbook = new XSSFWorkbook(new FileInputStream("src/main/resources/ru/ase/xls/AttributesGroup.xlsx"));
        Sheet sheet = workbook.getSheet("Иерархия классов".strip());

        String groupAttributeHeader = "Группа атрибутов";
        String nameAttributeHeader = "Наименование атрибута";
        String branchHeader = "Ветка итогового класса";


        Iterator<Row> rowIterator = sheet.rowIterator();
        Map<String, Integer> headerMap = XLSParseUtils.readHeaders(rowIterator.next().cellIterator());

        XLSParseUtils.validateHeader(headerMap, groupAttributeHeader);
        XLSParseUtils.validateHeader(headerMap, nameAttributeHeader);

        Map<String, TagAttributeGroup> classMap = new HashMap<>();
        String root = "DMP Пакш";
        classMap.put(root, new TagAttributeGroup(UUID.randomUUID(), null, root));

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            String branch = XLSParseUtils.getStringCellValue(row, headerMap.get(branchHeader));
            String group = XLSParseUtils.getStringCellValue(row, headerMap.get(groupAttributeHeader));
            String attribute = XLSParseUtils.getStringCellValue(row, headerMap.get(nameAttributeHeader));

            var branchArray = branch.split(" ➔ ");
            int lastIndex = branchArray.length - 1;

            if (!classMap.containsKey(branchArray[lastIndex])) classMap.put(
                    branchArray[lastIndex],
                    new TagAttributeGroup(UUID.randomUUID(), classMap.get(branchArray[lastIndex - 1]), branchArray[lastIndex]));


        }
        System.out.println(classMap);
        System.out.println(classMap.containsKey("Шлюз"));
        System.out.println(classMap.containsKey("Оборудование такелажное"));
    }


}
