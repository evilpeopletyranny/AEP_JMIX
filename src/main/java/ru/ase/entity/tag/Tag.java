package ru.ase.entity.tag;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.Comment;
import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import ru.ase.entity.DesignCondition;
import ru.ase.entity.event.InitialEvent;
import ru.ase.entity.structure.PBSCode;
import ru.ase.entity.structure.unit.building.Building;
import ru.ase.entity.structure.unit.System;
import ru.ase.entity.tag.classifier.TagClassifier;

import javax.xml.bind.annotation.*;
import java.util.Set;
import java.util.UUID;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JmixEntity
@Table(name = "TAG", indexes = {
        @Index(name = "IDX_TAG_BUILDING", columnList = "BUILDING_ID"),
        @Index(name = "IDX_TAG_SYSTEM", columnList = "SYSTEM_ID"),
        @Index(name = "IDX_TAG_PBS_CODE", columnList = "PBS_CODE_ID"),
        @Index(name = "IDX_TAG_CLASSIFIER", columnList = "CLASSIFIER_ID")
})
@Entity
public class Tag {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Comment("Project position kks code.")
    @Column(name = "KKS_CODE", nullable = false)
    @NotNull
    private String kksCode;

    @Column(name = "NAME", nullable = false)
    @NotNull
    private String name;

    @NotNull
    @Column(name = "DESCRIPTION_ENG", nullable = false)
    private String descriptionEn;

    @OnDeleteInverse(DeletePolicy.DENY)
    @JoinColumn(name = "CLASSIFIER_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private TagClassifier classifier;

    @XmlElementWrapper(name = "designConditions")
    @XmlElement(name = "designCondition")
    @JoinTable(name = "TAG_DESIGN_CONDITION_LINK",
            joinColumns = @JoinColumn(name = "TAG_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "DESIGN_CONDITION_ID", referencedColumnName = "ID"))
    @ManyToMany
    private Set<DesignCondition> designConditions;

    @XmlElementWrapper(name = "initialEvents")
    @XmlElement(name = "initialEvent")
    @OnDeleteInverse(DeletePolicy.UNLINK)
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
    @JoinColumn(name = "BUILDING_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Building building;

    @OnDeleteInverse(DeletePolicy.DENY)
    @JoinColumn(name = "SYSTEM_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private System system;

    @OnDeleteInverse(DeletePolicy.DENY)
    @JoinColumn(name = "PBS_CODE_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private PBSCode pbsCode;

    @Composition
    @OnDeleteInverse(DeletePolicy.DENY)
    @OneToMany(mappedBy = "tag")
    private Set<TagAttributeValue> attributes;

    @Column(name = "REVISION")
    private String revision;

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public Tag() {
    }

    public Tag(UUID id, String kksCode, String nameRu, String nameEng, TagClassifier classifier, Set<DesignCondition> designConditions, Set<InitialEvent> initialEvents, String description, Building building, System system, PBSCode pbsCode, Set<TagAttributeValue> attributes, String revision) {
        this.id = id;
        this.kksCode = kksCode;
        this.name = nameRu;
        this.descriptionEn = nameEng;
        this.classifier = classifier;
        this.designConditions = designConditions;
        this.initialEvents = initialEvents;
        this.description = description;
        this.building = building;
        this.system = system;
        this.pbsCode = pbsCode;
        this.attributes = attributes;
        this.revision = revision;
    }

    public void setNameRu(String nameRu) {
        this.name = nameRu;
    }

    public String getNameEng() {
        return descriptionEn;
    }

    public void setNameEng(String nameEng) {
        this.descriptionEn = nameEng;
    }

    public String getNameRu() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TagClassifier getClassifier() {
        return classifier;
    }

    public void setClassifier(TagClassifier classifier) {
        this.classifier = classifier;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", kksCode='" + kksCode + '\'' +
                ", nameRu='" + name + '\'' +
                ", nameEng='" + descriptionEn + '\'' +
                ", classifier=" + classifier +
                ", designConditions=" + designConditions +
                ", initialEvents=" + initialEvents +
                ", description='" + description + '\'' +
                ", building=" + building +
                ", system=" + system +
                ", pbsCode=" + pbsCode +
                ", attributes=" + attributes +
                ", revision='" + revision + '\'' +
                '}';
    }
}