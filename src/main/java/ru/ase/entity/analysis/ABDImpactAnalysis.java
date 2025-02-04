package ru.ase.entity.analysis;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.OnDelete;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import ru.ase.entity.analysis.attribute.Criticality;
import ru.ase.entity.approximation.Approximation;

@JmixEntity
@Table(name = "ABD_IMPACT_ANALYSIS", indexes = {
        @Index(name = "IDX_ABD_IMPACT_ANALYSIS_IMPACT_ANALYSIS", columnList = "IMPACT_ANALYSIS_ID"),
        @Index(name = "IDX_ABD_IMPACT_ANALYSIS_APPROXIMATION", columnList = "APPROXIMATION_ID"),
        @Index(name = "IDX_ABD_IMPACT_ANALYSIS_CRITICALITY", columnList = "CRITICALITY_ID")
})
@Entity
public class ABDImpactAnalysis extends Analysis {

    @OnDeleteInverse(DeletePolicy.DENY)
    @OnDelete(DeletePolicy.UNLINK)
    @JoinColumn(name = "IMPACT_ANALYSIS_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ImpactAnalysis impactAnalysis;

    @Column(name = "NON_IMPLEMENTATION_JUSTIFICATION")
    private String nonImplementationJustification;

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

    public String getNonImplementationJustification() {
        return nonImplementationJustification;
    }

    public void setNonImplementationJustification(String nonImplementationJustification) {
        this.nonImplementationJustification = nonImplementationJustification;
    }

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

    public ImpactAnalysis getImpactAnalysis() {
        return impactAnalysis;
    }

    public void setImpactAnalysis(ImpactAnalysis impactAnalysis) {
        this.impactAnalysis = impactAnalysis;
    }

}