package ru.ase.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@JmixEntity
@Table(name = "DESIGN_CONDITION")
@Entity
public class DesignCondition extends Dictionary {
}