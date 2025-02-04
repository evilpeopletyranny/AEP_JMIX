package ru.ase.entity.tag;

import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import ru.ase.entity.Dictionary;

@JmixEntity
@Table(name = "TAG_GROUP")
@Entity
public class TagGroup extends Dictionary {
}