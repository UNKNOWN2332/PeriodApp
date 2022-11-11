package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.myapp.domain.enumeration.DatesOfPeriod;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
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

    @OneToMany(mappedBy = "periodId")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "accId", "periodId" }, allowSetters = true)
    private Set<Pay> payIds = new HashSet<>();

    @OneToMany(mappedBy = "periodId")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "accId", "periodId" }, allowSetters = true)
    private Set<InfoPaid> infoPaids = new HashSet<>();

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

    public Set<Pay> getPayIds() {
        return this.payIds;
    }

    public void setPayIds(Set<Pay> pays) {
        if (this.payIds != null) {
            this.payIds.forEach(i -> i.setPeriodId(null));
        }
        if (pays != null) {
            pays.forEach(i -> i.setPeriodId(this));
        }
        this.payIds = pays;
    }

    public Period payIds(Set<Pay> pays) {
        this.setPayIds(pays);
        return this;
    }

    public Period addPayId(Pay pay) {
        this.payIds.add(pay);
        pay.setPeriodId(this);
        return this;
    }

    public Period removePayId(Pay pay) {
        this.payIds.remove(pay);
        pay.setPeriodId(null);
        return this;
    }

    public Set<InfoPaid> getInfoPaids() {
        return this.infoPaids;
    }

    public void setInfoPaids(Set<InfoPaid> infoPaids) {
        if (this.infoPaids != null) {
            this.infoPaids.forEach(i -> i.setPeriodId(null));
        }
        if (infoPaids != null) {
            infoPaids.forEach(i -> i.setPeriodId(this));
        }
        this.infoPaids = infoPaids;
    }

    public Period infoPaids(Set<InfoPaid> infoPaids) {
        this.setInfoPaids(infoPaids);
        return this;
    }

    public Period addInfoPaid(InfoPaid infoPaid) {
        this.infoPaids.add(infoPaid);
        infoPaid.setPeriodId(this);
        return this;
    }

    public Period removeInfoPaid(InfoPaid infoPaid) {
        this.infoPaids.remove(infoPaid);
        infoPaid.setPeriodId(null);
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
