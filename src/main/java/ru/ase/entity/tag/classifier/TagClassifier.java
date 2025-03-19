package ru.ase.entity.tag.classifier;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@JmixEntity
@Table(name = "TAG_CLASSIFIER", indexes = {
        @Index(name = "IDX_TAG_CLASSIFIER_PARENT_CLASSIFIER", columnList = "PARENT_CLASSIFIER_ID")
})
@Entity
public class TagClassifier {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @OnDeleteInverse(DeletePolicy.UNLINK)
    @JoinColumn(name = "PARENT_CLASSIFIER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private TagClassifier parentClassifier;

    @InstanceName
    @Column(name = "NAME", nullable = false)
    @Lob
    @NotNull
    private String name;

    public TagClassifier() {
    }

    public TagClassifier(UUID id, TagClassifier parentClassifier, String name) {
        this.id = id;
        this.parentClassifier = parentClassifier;
        this.name = name;
    }

    public TagClassifier getParentClassifier() {
        return parentClassifier;
    }

    public void setParentClassifier(TagClassifier parentClassifier) {
        this.parentClassifier = parentClassifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TagClassifier{" +
                "id=" + id +
                ", parentClassifier=" + parentClassifier +
                ", name='" + name + '\'' +
                '}';
    }
}