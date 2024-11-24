package ru.ase.entity.task;

import io.jmix.core.DeletePolicy;
import io.jmix.core.annotation.DeletedBy;
import io.jmix.core.annotation.DeletedDate;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDelete;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import ru.ase.entity.Report;
import ru.ase.entity.analysis.Stage;
import ru.ase.entity.approximation.Approximation;
import ru.ase.entity.document.Document;
import ru.ase.entity.event.InitialEvent;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@JmixEntity
@Table(name = "TASK", indexes = {
        @Index(name = "IDX_TASK_STAGE", columnList = "STAGE_ID"),
        @Index(name = "IDX_TASK_INITIAL_EVENT", columnList = "INITIAL_EVENT_ID"),
        @Index(name = "IDX_TASK_APPROXIMATIONS", columnList = "")
})
@Entity(name = "Task_")
public class Task {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "CODE", nullable = false)
    @NotNull
    private String code;

    @Column(name = "ANNOTATION")
    @Lob
    private String annotation;

    @Column(name = "REVISION", nullable = false)
    @NotNull
    private String revision;

    @OnDeleteInverse(DeletePolicy.DENY)
    @OnDelete(DeletePolicy.UNLINK)
    @JoinColumn(name = "STAGE_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Stage stage;

    @OnDeleteInverse(DeletePolicy.DENY)
    @OnDelete(DeletePolicy.UNLINK)
    @JoinColumn(name = "INITIAL_EVENT_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private InitialEvent initialEvent;

    @OnDeleteInverse(DeletePolicy.DENY)
    @OnDelete(DeletePolicy.DENY)
    @JoinTable(name = "TASK_DOCUMENT_LINK",
            joinColumns = @JoinColumn(name = "TASK_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "DOCUMENT_ID", referencedColumnName = "ID"))
    @ManyToMany
    private Set<Document> documents;

    @OnDeleteInverse(DeletePolicy.DENY)
    @OnDelete(DeletePolicy.DENY)
    @JoinTable(name = "TASK_REPORT_LINK",
            joinColumns = @JoinColumn(name = "TASK_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "REPORT_ID", referencedColumnName = "ID"))
    @ManyToMany
    private Set<Report> reports;

    @JoinTable(name = "TASK_APPROXIMATION_LINK",
            joinColumns = @JoinColumn(name = "TASK_ID"),
            inverseJoinColumns = @JoinColumn(name = "APPROXIMATION_ID"))
    @ManyToMany
    @OnDeleteInverse(DeletePolicy.UNLINK)
    @OnDelete(DeletePolicy.DENY)
    private List<Approximation> approximations;

    @CreatedBy
    @Column(name = "CREATED_BY")
    private String createdBy;

    @CreatedDate
    @Column(name = "CREATED_DATE")
    private OffsetDateTime createdDate;

    @DeletedBy
    @Column(name = "DELETED_BY")
    private String deletedBy;

    @DeletedDate
    @Column(name = "DELETED_DATE")
    private OffsetDateTime deletedDate;

    public void setApproximations(List<Approximation> approximations) {
        this.approximations = approximations;
    }

    public List<Approximation> getApproximations() {
        return approximations;
    }

    public Set<Report> getReports() {
        return reports;
    }

    public void setReports(Set<Report> reports) {
        this.reports = reports;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public InitialEvent getInitialEvent() {
        return initialEvent;
    }

    public void setInitialEvent(InitialEvent initialEvent) {
        this.initialEvent = initialEvent;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
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