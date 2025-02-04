package ru.ase.entity.analysis.attribute;

import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import ru.ase.entity.Dictionary;

@JmixEntity
@Table(name = "CRITICALITY")
@Entity
public class Criticality extends Dictionary {
}