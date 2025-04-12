package ru.ase.entity.structure.unit.building;

import io.jmix.core.metamodel.annotation.Comment;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import ru.ase.entity.structure.PBSCode;
import ru.ase.entity.structure.unit.StructuralUnit;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;

@XmlRootElement
@Comment("Description of the structure block - building.")
@JmixEntity
@Table(name = "BUILDING")
@Entity
public class Building extends StructuralUnit {
    public Building() {
    }

    public Building(UUID id, String kksCode, PBSCode pbsCode, String description) {
        super(id, kksCode, pbsCode, description);
    }

}