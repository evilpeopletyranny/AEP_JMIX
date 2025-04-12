package ru.ase.xls.tag;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.ase.entity.structure.PBSCode;
import ru.ase.entity.tag.Tag;
import ru.ase.entity.tag.classifier.TagClassifier;
import ru.ase.xls.utils.XLSParseUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public class TagTest {
    public static void main(String[] args) throws IOException {
        Workbook workbook = new XSSFWorkbook(new FileInputStream("src/main/resources/ru/ase/xls/Tags.xlsx"));
        Sheet sheet = workbook.getSheetAt(0);

        String kksCodeHeader = "Наименование";
        String nameRuHeader = "Описание";
        String nameEngHeader = "Описание ENG";
        String classificationHeader = "Классификация";
        String pbdCodeHeader = "PBS";
        String systemHeader = "Система";
        String buildingHeader = "ЗданиеRM";

        Iterator<Row> rowIterator = sheet.rowIterator();
        Map<String, Integer> headerMap = XLSParseUtils.readHeaders(rowIterator.next().cellIterator());

        XLSParseUtils.validateHeader(headerMap, kksCodeHeader);
        XLSParseUtils.validateHeader(headerMap, nameRuHeader);
        XLSParseUtils.validateHeader(headerMap, nameEngHeader);
        XLSParseUtils.validateHeader(headerMap, classificationHeader);
        XLSParseUtils.validateHeader(headerMap, pbdCodeHeader);
        XLSParseUtils.validateHeader(headerMap, systemHeader);
        XLSParseUtils.validateHeader(headerMap, buildingHeader);

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            UUID id = UUID.randomUUID();
            String kksCode = XLSParseUtils.getStringCellValue(row, headerMap.get(kksCodeHeader));
            String nameRu = XLSParseUtils.getStringCellValue(row, headerMap.get(nameRuHeader));
            String nameEn = XLSParseUtils.getStringCellValue(row, headerMap.get(nameEngHeader));
            TagClassifier classifier = new TagClassifier(UUID.randomUUID(), null, XLSParseUtils.getStringCellValue(row, headerMap.get(classificationHeader)));
            String pbsCode = XLSParseUtils.getStringCellValue(row, headerMap.get(pbdCodeHeader));
            String system = XLSParseUtils.getStringCellValue(row, headerMap.get(systemHeader));
            String building = XLSParseUtils.getStringCellValue(row, headerMap.get(buildingHeader));

            Tag tag = new Tag(
                    id,
                    kksCode,
                    nameRu,
                    nameEn,
                    classifier,
                    null,
                    null,
                    "",
                    null,
                    null,
                    null,
                    null,
                    null);

            System.out.println(tag);

        }


    }
}
