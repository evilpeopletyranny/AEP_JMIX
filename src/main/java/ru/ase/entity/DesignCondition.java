package ru.ase.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@JmixEntity
@Table(name = "DESIGN_CONDITION")
@Entity
public class DesignCondition extends Dictionary {
}