package ru.ase.entity.approximation;

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
import ru.ase.entity.plug.Limitation;
import ru.ase.entity.tag.Tag;
import ru.ase.entity.tag.TagAttribute;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@JmixEntity
@Table(name = "APPROXIMATION", indexes = {
        @Index(name = "IDX_APPROXIMATION_TAG", columnList = "TAG_ID"),
        @Index(name = "IDX_APPROXIMATION_TAG_ATTRIBUTE", columnList = "TAG_ATTRIBUTE_ID"),
        @Index(name = "IDX_APPROXIMATION_LIMITATION", columnList = "LIMITATION_ID")
})
@Entity
public class Approximation {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "JUSTIFICATION", nullable = false)
    @Lob
    @NotNull
    private String justification;

    @OnDeleteInverse(DeletePolicy.UNLINK)
    @OnDelete(DeletePolicy.DENY)
    @JoinColumn(name = "LIMITATION_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Limitation limitation;

    @Column(name = "FILE", nullable = false)
    @NotNull
    private String file;

    @Column(name = "REVISION", nullable = false)
    @NotNull
    private String revision;

    @OnDeleteInverse(DeletePolicy.DENY)
    @OnDelete(DeletePolicy.UNLINK)
    @JoinColumn(name = "TAG_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Tag tag;

    @OnDeleteInverse(DeletePolicy.DENY)
    @OnDelete(DeletePolicy.UNLINK)
    @JoinColumn(name = "TAG_ATTRIBUTE_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private TagAttribute tagAttribute;

    @Column(name = "CALC_VALUE", nullable = false, precision = 19, scale = 2)
    @NotNull
    private BigDecimal calculationValue;

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

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Limitation getLimitation() {
        return limitation;
    }

    public void setLimitation(Limitation limitation) {
        this.limitation = limitation;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public BigDecimal getCalculationValue() {
        return calculationValue;
    }

    public void setCalculationValue(BigDecimal calculationValue) {
        this.calculationValue = calculationValue;
    }

    public TagAttribute getTagAttribute() {
        return tagAttribute;
    }

    public void setTagAttribute(TagAttribute tagAttribute) {
        this.tagAttribute = tagAttribute;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
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