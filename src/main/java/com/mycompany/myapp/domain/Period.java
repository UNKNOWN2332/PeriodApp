package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.myapp.domain.enumeration.DatesOfPeriod;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Period.
 */
@Entity
@Table(name = "period")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Period implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Size(min = 3, max = 25)
    @Column(name = "period_name", length = 25)
    private String periodName;

    @Column(name = "create_at")
    private Instant createAt;

    @DecimalMin(value = "0")
    @Column(name = "amount")
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "dates_of_period")
    private DatesOfPeriod datesOfPeriod;

    @JsonIgnoreProperties(value = { "accId", "periodId" }, allowSetters = true)
    @OneToOne(mappedBy = "periodId")
    private Pay payId;

    @JsonIgnoreProperties(value = { "accId", "periodId" }, allowSetters = true)
    @OneToOne(mappedBy = "periodId")
    private InfoPaid infoPaid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Period id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPeriodName() {
        return this.periodName;
    }

    public Period periodName(String periodName) {
        this.setPeriodName(periodName);
        return this;
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }

    public Instant getCreateAt() {
        return this.createAt;
    }

    public Period createAt(Instant createAt) {
        this.setCreateAt(createAt);
        return this;
    }

    public void setCreateAt(Instant createAt) {
        this.createAt = createAt;
    }

    public Double getAmount() {
        return this.amount;
    }

    public Period amount(Double amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public DatesOfPeriod getDatesOfPeriod() {
        return this.datesOfPeriod;
    }

    public Period datesOfPeriod(DatesOfPeriod datesOfPeriod) {
        this.setDatesOfPeriod(datesOfPeriod);
        return this;
    }

    public void setDatesOfPeriod(DatesOfPeriod datesOfPeriod) {
        this.datesOfPeriod = datesOfPeriod;
    }

    public Pay getPayId() {
        return this.payId;
    }

    public void setPayId(Pay pay) {
        if (this.payId != null) {
            this.payId.setPeriodId(null);
        }
        if (pay != null) {
            pay.setPeriodId(this);
        }
        this.payId = pay;
    }

    public Period payId(Pay pay) {
        this.setPayId(pay);
        return this;
    }

    public InfoPaid getInfoPaid() {
        return this.infoPaid;
    }

    public void setInfoPaid(InfoPaid infoPaid) {
        if (this.infoPaid != null) {
            this.infoPaid.setPeriodId(null);
        }
        if (infoPaid != null) {
            infoPaid.setPeriodId(this);
        }
        this.infoPaid = infoPaid;
    }

    public Period infoPaid(InfoPaid infoPaid) {
        this.setInfoPaid(infoPaid);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Period)) {
            return false;
        }
        return id != null && id.equals(((Period) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Period{" +
            "id=" + getId() +
            ", periodName='" + getPeriodName() + "'" +
            ", createAt='" + getCreateAt() + "'" +
            ", amount=" + getAmount() +
            ", datesOfPeriod='" + getDatesOfPeriod() + "'" +
            "}";
    }
}
