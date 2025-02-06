package ru.ase.entity.tag;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;
import java.util.UUID;

@XmlRootElement
@JmixEntity
@Table(name = "TAG_TYPE")
@Entity
public class TagType {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @InstanceName
    @Column(name = "NAME", nullable = false)
    @Lob
    @NotNull
    private String name;

    @OnDeleteInverse(DeletePolicy.UNLINK)
    @JoinTable(name = "TAG_TYPE_TAG_GROUP_LINK",
            joinColumns = @JoinColumn(name = "TAG_TYPE_ID"),
            inverseJoinColumns = @JoinColumn(name = "TAG_GROUP_ID"))
    @ManyToMany
    private Set<TagGroup> tagGroups;

    public Set<TagGroup> getTagGroups() {
        return tagGroups;
    }

    public void setTagGroups(Set<TagGroup> tagGroups) {
        this.tagGroups = tagGroups;
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