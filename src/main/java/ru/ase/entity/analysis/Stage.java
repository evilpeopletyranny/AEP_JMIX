package ru.ase.entity.analysis;

import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import ru.ase.entity.Dictionary;

@JmixEntity
@Table(name = "STAGE")
@Entity
public class Stage extends Dictionary {
}