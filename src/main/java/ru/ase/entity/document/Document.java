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

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@JmixEntity
@Table(name = "DOCUMENT", indexes = {
        @Index(name = "IDX_DOCUMENT_DOCUMENT_TYPE", columnList = "DOCUMENT_TYPE_ID")
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

    @OnDeleteInverse(DeletePolicy.UNLINK)
    @OnDelete(DeletePolicy.DENY)
    @JoinColumn(name = "DOCUMENT_TYPE_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Document documentType;

    @OnDeleteInverse(DeletePolicy.DENY)
    @OnDelete(DeletePolicy.DENY)
    @JoinTable(name = "DOCUMENT_INITIAL_EVENT_LINK",
            joinColumns = @JoinColumn(name = "DOCUMENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "INITIAL_EVENT_ID"))
    @ManyToMany
    private Set<InitialEvent> initialEvents;

    @CreatedDate
    @Column(name = "CREATED_DATE")
    private OffsetDateTime createdDate;

    @DeletedBy
    @Column(name = "DELETED_BY")
    private String deletedBy;

    @DeletedDate
    @Column(name = "DELETED_DATE")
    private OffsetDateTime deletedDate;

    public Set<InitialEvent> getInitialEvents() {
        return initialEvents;
    }

    public void setInitialEvents(Set<InitialEvent> initialEvents) {
        this.initialEvents = initialEvents;
    }

    public Document getDocumentType() {
        return documentType;
    }

    public void setDocumentType(Document documentType) {
        this.documentType = documentType;
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