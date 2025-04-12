package ru.ase.entity.structure.unit;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDelete;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.Comment;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import ru.ase.entity.structure.PBSCode;

import java.util.UUID;

@Comment("General description of the structure unit.")
@JmixEntity
@MappedSuperclass
public abstract class StructuralUnit {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Comment("KKS code of this structural unit.")
    @Column(name = "KKS_CODE", nullable = false)
    @NotNull
    private String kksCode;

    @Comment("PBS code of this structural unit.")
    @OnDeleteInverse(DeletePolicy.DENY)
    @OnDelete(DeletePolicy.UNLINK)
    @JoinColumn(name = "PBS_CODE_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private PBSCode pbsCode;

    @InstanceName
    @Comment("Description of this structural unit.")
    @Column(name = "DESCRIPTION")
    @Lob
    private String description;

    public StructuralUnit() {
    }

    public StructuralUnit(UUID id, String kksCode, PBSCode pbsCode, String description) {
        this.id = id;
        this.kksCode = kksCode;
        this.pbsCode = pbsCode;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PBSCode getPbsCode() {
        return pbsCode;
    }

    public void setPbsCode(PBSCode pbsCode) {
        this.pbsCode = pbsCode;
    }

    public String getKksCode() {
        return kksCode;
    }

    public void setKksCode(String kksCode) {
        this.kksCode = kksCode;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}