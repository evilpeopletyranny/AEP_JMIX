package ru.ase.entity.analysis;

import io.jmix.core.DeletePolicy;
import io.jmix.core.annotation.DeletedBy;
import io.jmix.core.annotation.DeletedDate;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDelete;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import ru.ase.entity.approximation.Approximation;
import ru.ase.entity.document.Document;

import java.time.OffsetDateTime;
import java.util.UUID;

@JmixEntity
@Table(name = "IMPACT_ANALYSIS", indexes = {
        @Index(name = "IDX_IMPACT_ANALYSIS_CHANGE_REQUEST", columnList = "CHANGE_REQUEST_ID"),
        @Index(name = "IDX_IMPACT_ANALYSIS_CHANGE_CONFIGURATION_BASELINE", columnList = "CHANGE_CONFIGURATION_BASELINE_ID"),
        @Index(name = "IDX_IMPACT_ANALYSIS_IMPLEMENTATION_CONFIGURATION_BASELINE", columnList = "IMPLEMENTATION_CONFIGURATION_BASELINE_ID"),
        @Index(name = "IDX_IMPACT_ANALYSIS_IMPLEMENTATION_OBJECT", columnList = "IMPLEMENTATION_OBJECT_ID"),
        @Index(name = "IDX_IMPACT_ANALYSIS_STATUS", columnList = "STATUS_ID"),
        @Index(name = "IDX_IMPACT_ANALYSIS_DOCUMENT", columnList = "DOCUMENT_ID")
})
@Entity
public class ImpactAnalysis {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "CODE", nullable = false)
    @NotNull
    private String code;

    @InstanceName
    @Column(name = "DESCRIPTION", nullable = false)
    @NotNull
    private String description;

    @OnDeleteInverse(DeletePolicy.DENY)
    @OnDelete(DeletePolicy.UNLINK)
    @JoinColumn(name = "CHANGE_REQUEST_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ChangeRequest changeRequest;

    @OnDeleteInverse(DeletePolicy.DENY)
    @OnDelete(DeletePolicy.UNLINK)
    @JoinColumn(name = "CHANGE_CONFIGURATION_BASELINE_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Stage changeConfigurationBaseline;

    @OnDeleteInverse(DeletePolicy.DENY)
    @OnDelete(DeletePolicy.UNLINK)
    @JoinColumn(name = "IMPLEMENTATION_CONFIGURATION_BASELINE_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Stage implementationConfigurationBaseline;

    @OnDeleteInverse(DeletePolicy.DENY)
    @OnDelete(DeletePolicy.UNLINK)
    @JoinColumn(name = "IMPLEMENTATION_OBJECT_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Approximation implementationObject;

    @OnDeleteInverse(DeletePolicy.DENY)
    @OnDelete(DeletePolicy.UNLINK)
    @JoinColumn(name = "STATUS_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Status status;

    @OnDeleteInverse(DeletePolicy.DENY)
    @OnDelete(DeletePolicy.UNLINK)
    @JoinColumn(name = "DOCUMENT_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Document document;

    @Column(name = "REVISION", nullable = false)
    @NotNull
    private String revision;

    @DeletedBy
    @Column(name = "DELETED_BY")
    private String deletedBy;

    @DeletedDate
    @Column(name = "DELETED_DATE")
    private OffsetDateTime deletedDate;

    @CreatedBy
    @Column(name = "CREATED_BY")
    private String createdBy;

    @CreatedDate
    @Column(name = "CREATED_DATE")
    private OffsetDateTime createdDate;

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Approximation getImplementationObject() {
        return implementationObject;
    }

    public void setImplementationObject(Approximation implementationObject) {
        this.implementationObject = implementationObject;
    }

    public Stage getImplementationConfigurationBaseline() {
        return implementationConfigurationBaseline;
    }

    public void setImplementationConfigurationBaseline(Stage implementationConfigurationBaseline) {
        this.implementationConfigurationBaseline = implementationConfigurationBaseline;
    }

    public Stage getChangeConfigurationBaseline() {
        return changeConfigurationBaseline;
    }

    public void setChangeConfigurationBaseline(Stage changeConfigurationBaseline) {
        this.changeConfigurationBaseline = changeConfigurationBaseline;
    }

    public ChangeRequest getChangeRequest() {
        return changeRequest;
    }

    public void setChangeRequest(ChangeRequest changeRequest) {
        this.changeRequest = changeRequest;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}