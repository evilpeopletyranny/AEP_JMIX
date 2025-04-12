package ru.ase.entity.structure.unit;

import io.jmix.core.metamodel.annotation.Comment;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import ru.ase.entity.structure.PBSCode;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;

@XmlRootElement
@Comment("Description of the structure block - system.")
@JmixEntity
@Table(name = "SYSTEM_")
@Entity(name = "System_")
public class System extends StructuralUnit {
    public System() {
    }

    public System(UUID id, String kksCode, PBSCode pbsCode, String description) {
        super(id, kksCode, pbsCode, description);
    }
}