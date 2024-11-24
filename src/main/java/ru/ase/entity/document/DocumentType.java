package ru.ase.entity.document;

import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import ru.ase.entity.Dictionary;

@JmixEntity
@Table(name = "DOCUMENT_TYPE")
@Entity
public class DocumentType extends Dictionary {
}