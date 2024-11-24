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

import java.time.OffsetDateTime;
import java.util.UUID;

@JmixEntity
@Table(name = "ABD_IMPACT_ANALYSIS", indexes = {
        @Index(name = "IDX_ABD_IMPACT_ANALYSIS_IMPACT_ANALYSIS", columnList = "IMPACT_ANALYSIS_ID"),
        @Index(name = "IDX_ABD_IMPACT_ANALYSIS_CHANGE_CONFIGURATION_BASELINE", columnList = "CHANGE_CONFIGURATION_BASELINE_ID"),
        @Index(name = "IDX_ABD_IMPACT_ANALYSIS_IMPLEMENTATION_CONFIGURATION_BASELINE", columnList = "IMPLEMENTATION_CONFIGURATION_BASELINE_ID"),
        @Index(name = "IDX_ABD_IMPACT_ANALYSIS_IMPLEMENTATION_OBJECT", columnList = "IMPLEMENTATION_OBJECT_ID"),
        @Index(name = "IDX_ABD_IMPACT_ANALYSIS_STATUS", columnList = "STATUS_ID"),
        @Index(name = "IDX_ABD_IMPACT_ANALYSIS_APPROXIMATION", columnList = "APPROXIMATION_ID"),
        @Index(name = "IDX_ABD_IMPACT_ANALYSIS_CRITICALITY", columnList = "CRITICALITY_ID")
})
@Entity
public class ABDImpactAnalysis {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "CODE", nullable = false)
    @NotNull
    private String code;

    @InstanceName
    @Column(name = "DESCRIPTION")
    @Lob
    private String description;

    @OnDeleteInverse(DeletePolicy.DENY)
    @OnDelete(DeletePolicy.UNLINK)
    @JoinColumn(name = "IMPACT_ANALYSIS_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ImpactAnalysis impactAnalysis;

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
    @JoinColumn(name = "APPROXIMATION_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Approximation approximation;

    @OnDeleteInverse(DeletePolicy.DENY)
    @OnDelete(DeletePolicy.UNLINK)
    @JoinColumn(name = "CRITICALITY_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Criticality criticality;

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

    public Criticality getCriticality() {
        return criticality;
    }

    public void setCriticality(Criticality criticality) {
        this.criticality = criticality;
    }

    public Approximation getApproximation() {
        return approximation;
    }

    public void setApproximation(Approximation approximation) {
        this.approximation = approximation;
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

    public ImpactAnalysis getImpactAnalysis() {
        return impactAnalysis;
    }

    public void setImpactAnalysis(ImpactAnalysis impactAnalysis) {
        this.impactAnalysis = impactAnalysis;
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