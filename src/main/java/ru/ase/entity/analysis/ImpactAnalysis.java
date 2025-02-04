package ru.ase.entity.analysis;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.OnDelete;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import ru.ase.entity.analysis.attribute.ChangeRequest;
import ru.ase.entity.document.Document;

@JmixEntity
@Table(name = "IMPACT_ANALYSIS", indexes = {
        @Index(name = "IDX_IMPACT_ANALYSIS_CHANGE_REQUEST", columnList = "CHANGE_REQUEST_ID"),
        @Index(name = "IDX_IMPACT_ANALYSIS_DOCUMENT", columnList = "DOCUMENT_ID")
})
@Entity
public class ImpactAnalysis extends Analysis {

    @Composition
    @OneToOne(fetch = FetchType.LAZY)
    @OnDeleteInverse(DeletePolicy.DENY)
    @JoinColumn(name = "CHANGE_REQUEST_ID")
    private ChangeRequest changeRequest;

    @OnDeleteInverse(DeletePolicy.DENY)
    @OnDelete(DeletePolicy.UNLINK)
    @JoinColumn(name = "DOCUMENT_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Document document;

    @Column(name = "REVISION", nullable = false)
    @NotNull
    private String revision;

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

    public ChangeRequest getChangeRequest() {
        return changeRequest;
    }

    public void setChangeRequest(ChangeRequest changeRequest) {
        this.changeRequest = changeRequest;
    }

}