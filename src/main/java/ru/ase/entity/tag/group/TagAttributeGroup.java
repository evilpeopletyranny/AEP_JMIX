package ru.ase.entity.tag.group;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@JmixEntity
@Table(name = "TAG_ATTRIBUTE_GROUP", indexes = {
        @Index(name = "IDX_TAG_ATTRIBUTE_GROUP_UNQ", columnList = "NAME", unique = true),
        @Index(name = "IDX_TAG_ATTRIBUTE_GROUP_PARENT_GROUP", columnList = "PARENT_GROUP_ID")
})
@Entity
public class TagAttributeGroup {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @OnDeleteInverse(DeletePolicy.DENY)
    @JoinColumn(name = "PARENT_GROUP_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private TagAttributeGroup parentGroup;

    @InstanceName
    @Column(name = "NAME", nullable = false)
    @NotNull
    private String name;

    public TagAttributeGroup() {
    }

    public TagAttributeGroup(UUID id, TagAttributeGroup parentGroup, String name) {
        this.id = id;
        this.parentGroup = parentGroup;
        this.name = name;
    }

    public TagAttributeGroup getParentGroup() {
        return parentGroup;
    }

    public void setParentGroup(TagAttributeGroup parentGroup) {
        this.parentGroup = parentGroup;
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
}