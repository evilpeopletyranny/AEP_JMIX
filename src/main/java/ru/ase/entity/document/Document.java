package ru.ase.entity.document;

import io.jmix.core.DeletePolicy;
import io.jmix.core.annotation.DeletedBy;
import io.jmix.core.annotation.DeletedDate;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDelete;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.Comment;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import ru.ase.entity.event.InitialEvent;
import ru.ase.entity.plug.Requirement;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@JmixEntity
@Table(name = "DOCUMENT", indexes = {
        @Index(name = "IDX_DOCUMENT_DOCUMENT_TYPE", columnList = "DOCUMENT_TYPE_ID"),
        @Index(name = "IDX_DOCUMENT_REQUIREMENTS", columnList = "REQUIREMENTS_ID")
})
@Entity
public class Document {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Comment("Document code.")
    @Column(name = "CODE", nullable = false)
    @NotNull
    private String code;

    @CreatedBy
    @Column(name = "CREATED_BY")
    private String createdBy;

    @Comment("Document revision.")
    @Column(name = "REVISION", nullable = false)
    @NotNull
    private String revision;

    @NotNull
    @OnDeleteInverse(DeletePolicy.DENY)
    @OnDelete(DeletePolicy.UNLINK)
    @JoinColumn(name = "DOCUMENT_TYPE_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private DocumentType documentType;

    @OnDeleteInverse(DeletePolicy.DENY)
    @OnDelete(DeletePolicy.DENY)
    @JoinTable(name = "DOCUMENT_INITIAL_EVENT_LINK",
            joinColumns = @JoinColumn(name = "DOCUMENT_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "INITIAL_EVENT_ID", referencedColumnName = "ID"))
    @ManyToMany
    private Set<InitialEvent> initialEvents;

    @OnDeleteInverse(DeletePolicy.UNLINK)
    @OnDelete(DeletePolicy.UNLINK)
    @JoinColumn(name = "REQUIREMENTS_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Requirement requirements;

    @CreatedDate
    @Column(name = "CREATED_DATE")
    private OffsetDateTime createdDate;

    @DeletedBy
    @Column(name = "DELETED_BY")
    private String deletedBy;

    @DeletedDate
    @Column(name = "DELETED_DATE")
    private OffsetDateTime deletedDate;

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public Requirement getRequirements() {
        return requirements;
    }

    public void setRequirements(Requirement requirements) {
        this.requirements = requirements;
    }

    public Set<InitialEvent> getInitialEvents() {
        return initialEvents;
    }

    public void setInitialEvents(Set<InitialEvent> initialEvents) {
        this.initialEvents = initialEvents;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public OffsetDateTime getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(OffsetDateTime deletedDate) {
        this.deletedDate = deletedDate;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public OffsetDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(OffsetDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}