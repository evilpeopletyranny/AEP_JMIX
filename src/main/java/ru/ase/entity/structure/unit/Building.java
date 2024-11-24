package ru.ase.entity.structure.unit;

import io.jmix.core.metamodel.annotation.Comment;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Comment("Description of the structure block - building.")
@JmixEntity
@Table(name = "BUILDING")
@Entity
public class Building extends StructuralUnit {
}