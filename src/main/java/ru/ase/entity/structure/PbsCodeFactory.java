package ru.ase.entity.structure;

import io.jmix.core.DataManager;
import org.springframework.stereotype.Component;
import ru.ase.entity.tag.classifier.TagClassifier;

import java.util.UUID;

@Component
public class PbsCodeFactory {
    private final DataManager dataManager;

    public PbsCodeFactory(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public PBSCode create(String code) {
        PBSCode pbsCode = dataManager.create(PBSCode.class);
        pbsCode.setId(UUID.randomUUID());
        pbsCode.setCode(code);
        pbsCode.setDescription("");
        return pbsCode;
    }

    public PBSCode create(String code, String description) {
        PBSCode pbsCode = dataManager.create(PBSCode.class);
        pbsCode.setId(UUID.randomUUID());
        pbsCode.setCode(code);
        pbsCode.setDescription(description);
        return pbsCode;
    }
}
