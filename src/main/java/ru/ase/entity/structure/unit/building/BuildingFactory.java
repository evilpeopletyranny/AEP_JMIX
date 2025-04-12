package ru.ase.entity.structure.unit.building;

import io.jmix.core.DataManager;
import io.jmix.core.FetchPlan;
import org.springframework.stereotype.Component;
import ru.ase.entity.structure.PBSCode;

import java.util.UUID;

@Component
public class BuildingFactory {
    private final DataManager dataManager;

    public BuildingFactory(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public Building create(String name, String pbsCode) {
        Building building = dataManager.create(Building.class);
        building.setId(UUID.randomUUID());
        building.setKksCode(name);
        building.setPbsCode(findPbsCode(pbsCode));
        building.setKksCode("");
        return building;
    }

    private PBSCode findPbsCode(String pbsCode) {
        return dataManager.load(PBSCode.class)
                .query("select c from PBSCode c where c.code = :pbsCode")
                .parameter("pbsCode", pbsCode)
                .optional().orElse(null);
    }
}
