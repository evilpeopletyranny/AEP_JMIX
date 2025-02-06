package ru.ase.entity.tag;

import io.jmix.core.DeletePolicy;
import io.jmix.core.annotation.DeletedBy;
import io.jmix.core.annotation.DeletedDate;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDelete;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.Comment;
import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import ru.ase.entity.DesignCondition;
import ru.ase.entity.event.InitialEvent;
import ru.ase.entity.structure.PBSCode;
import ru.ase.entity.structure.unit.Building;
import ru.ase.entity.structure.unit.System;

import javax.xml.bind.annotation.*;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JmixEntity
@Table(name = "TAG", indexes = {
        @Index(name = "IDX_TAG_BUILDING", columnList = "BUILDING_ID"),
        @Index(name = "IDX_TAG_SYSTEM", columnList = "SYSTEM_ID"),
        @Index(name = "IDX_TAG_PBS_CODE", columnList = "PBS_CODE_ID"),
        @Index(name = "IDX_TAG_TAG_TYPE", columnList = "TAG_TYPE_ID")
})
@Entity
public class Tag {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @XmlElement(name = "tagType")
    @OnDeleteInverse(DeletePolicy.DENY)
    @OnDelete(DeletePolicy.UNLINK)
    @JoinColumn(name = "TAG_TYPE_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private TagType tagType;

    @Comment("Project position kks code.")
    @Column(name = "KKS_CODE", nullable = false)
    @NotNull
    private String kksCode;

    @XmlElementWrapper(name = "designConditions")
    @XmlElement(name = "designCondition")
    @OnDeleteInverse(DeletePolicy.UNLINK)
    @OnDelete(DeletePolicy.UNLINK)
    @JoinTable(name = "TAG_DESIGN_CONDITION_LINK",
            joinColumns = @JoinColumn(name = "TAG_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "DESIGN_CONDITION_ID", referencedColumnName = "ID"))
    @ManyToMany
    private Set<DesignCondition> designConditions;

    @XmlElementWrapper(name = "initialEvents")
    @XmlElement(name = "initialEvent")
    @OnDeleteInverse(DeletePolicy.UNLINK)
    @OnDelete(DeletePolicy.UNLINK)
    @JoinTable(name = "TAG_INITIAL_EVENT_LINK",
            joinColumns = @JoinColumn(name = "TAG_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "INITIAL_EVENT_ID", referencedColumnName = "ID"))
    @ManyToMany
    private Set<InitialEvent> initialEvents;

    @InstanceName
    @Comment("Description of the project position.")
    @Column(name = "DESCRIPTION")
    @Lob
    private String description;

    @NotNull
    @OnDeleteInverse(DeletePolicy.DENY)
    @OnDelete(DeletePolicy.UNLINK)
    @JoinColumn(name = "BUILDING_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Building building;

    @OnDeleteInverse(DeletePolicy.DENY)
    @OnDelete(DeletePolicy.UNLINK)
    @JoinColumn(name = "SYSTEM_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private System system;

    @OnDeleteInverse(DeletePolicy.DENY)
    @OnDelete(DeletePolicy.UNLINK)
    @JoinColumn(name = "PBS_CODE_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private PBSCode pbsCode;

    @Composition
    @OnDeleteInverse(DeletePolicy.DENY)
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "tag")
    private Set<TagAttributeValue> attributes;

    @Column(name = "REVISION", nullable = false)
    @NotNull
    private String revision;

    @CreatedBy
    @Column(name = "CREATED_BY")
    private String createdBy;

    @CreatedDate
    @Column(name = "CREATED_DATE")
    private OffsetDateTime createdDate;

    @DeletedBy
    @Column(name = "DELETED_BY")
    private String deletedBy;

    @DeletedDate
    @Column(name = "DELETED_DATE")
    private OffsetDateTime deletedDate;

    public TagType getTagType() {
        return tagType;
    }

    public void setTagType(TagType tagType) {
        this.tagType = tagType;
    }

    public void setAttributes(Set<TagAttributeValue> attributes) {
        this.attributes = attributes;
    }

    public Set<TagAttributeValue> getAttributes() {
        return attributes;
    }

    public Set<InitialEvent> getInitialEvents() {
        return initialEvents;
    }

    public void setInitialEvents(Set<InitialEvent> initialEvents) {
        this.initialEvents = initialEvents;
    }

    public Set<DesignCondition> getDesignConditions() {
        return designConditions;
    }

    public void setDesignConditions(Set<DesignCondition> designConditions) {
        this.designConditions = designConditions;
    }


    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public PBSCode getPbsCode() {
        return pbsCode;
    }

    public void setPbsCode(PBSCode pbsCode) {
        this.pbsCode = pbsCode;
    }

    public System getSystem() {
        return system;
    }

    public void setSystem(System system) {
        this.system = system;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKksCode() {
        return kksCode;
    }

    public void setKksCode(String kksCode) {
        this.kksCode = kksCode;
    }

    public OffsetDateTime getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(OffsetDateTime deletedDate) {
        this.deletedDate = deletedDate;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public OffsetDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(OffsetDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}