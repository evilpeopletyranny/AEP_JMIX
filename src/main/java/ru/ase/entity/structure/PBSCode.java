package ru.ase.entity.structure;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.Comment;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;

@XmlRootElement
@JmixEntity
@Table(name = "PBS_CODE")
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class PBSCode {
    @Comment("PBS code database identifier.")
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Comment("PBS code value.")
    @Column(name = "CODE", nullable = false)
    @NotNull
    @InstanceName
    private String code;

    @Lob
    @Comment("PBS code description. What object does the PBS code belong to ?")
    @Column(name = "DESCRIPTION")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}