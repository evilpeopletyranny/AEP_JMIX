package ru.ase.entity.event;

import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import ru.ase.entity.Dictionary;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@JmixEntity
@Table(name = "INITIAL_EVENT_GROUP")
@Entity
public class InitialEventGroup extends Dictionary {
}