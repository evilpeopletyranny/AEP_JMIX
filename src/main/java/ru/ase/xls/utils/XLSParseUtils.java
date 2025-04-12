package ru.ase.xls.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class XLSParseUtils {

    public static Map<String, Integer> readHeaders(Iterator<Cell> cellIterator) {
        Map<String, Integer> headerMap = new HashMap<>();
        while (cellIterator.hasNext()) {
            var cell = cellIterator.next();
            headerMap.put(cell.getStringCellValue().trim(), cell.getColumnIndex());
        }
        return headerMap;
    }

    public static void validateHeader(Map<String, Integer> headersMap, String header) {
        if (!headersMap.containsKey(header)) throw new IllegalArgumentException(header + " header missing");
    }

    public static String getStringCellValue(Row row, Integer cellInd) {
        return row.getCell(cellInd, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
                .getStringCellValue().trim();
    }
}
