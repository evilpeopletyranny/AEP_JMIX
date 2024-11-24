package ru.ase.entity.plug;

import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import ru.ase.entity.Dictionary;

@JmixEntity
@Table(name = "FILE")
@Entity(name = "File")
public class File extends Dictionary {
}