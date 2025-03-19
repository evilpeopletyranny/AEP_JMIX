package ru.ase.xls;

import io.jmix.core.DataManager;
import org.springframework.stereotype.Component;

@Component
public class TagClassifierXlsxImportService {
    private final TagClassifierXlsxParser tagClassifierXlsxParser;
    private final DataManager dataManager;

    public TagClassifierXlsxImportService(TagClassifierXlsxParser tagClassifierXlsxParser, DataManager dataManager) {
        this.tagClassifierXlsxParser = tagClassifierXlsxParser;
        this.dataManager = dataManager;
    }


}