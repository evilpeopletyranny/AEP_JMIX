package ru.ase.entity.tag.attibute;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import ru.ase.entity.tag.group.TagAttributeGroup;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;

@XmlRootElement
@JmixEntity
@Table(name = "TAG_ATTRIBUTE", indexes = {
        @Index(name = "IDX_TAG_TAG_ATTRIBUTE_UNQ", columnList = "NAME", unique = true),
        @Index(name = "IDX_TAG_ATTRIBUTE_ATTRIBUTE_GROUP", columnList = "ATTRIBUTE_GROUP_ID")
})
@Entity
public class TagAttribute {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @OnDeleteInverse(DeletePolicy.DENY)
    @JoinColumn(name = "ATTRIBUTE_GROUP_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private TagAttributeGroup attributeGroup;

    @Column(name = "NAME", nullable = false)
    @NotNull
    private String name;

    public TagAttributeGroup getAttributeGroup() {
        return attributeGroup;
    }

    public void setAttributeGroup(TagAttributeGroup attributeGroup) {
        this.attributeGroup = attributeGroup;
    }

    public String getName() {
        return name;
    }

    public String getAttribute() {
        return name;
    }

    public void setAttribute(String attribute) {
        this.name = attribute;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}