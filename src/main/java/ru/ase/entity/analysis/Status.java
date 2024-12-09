package ru.ase.entity.analysis;

import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import ru.ase.entity.Dictionary;

@Table(name = "STATUS")
@JmixEntity
@Entity
public class Status extends Dictionary {

}