package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A InfoPaid.
 */
@Entity
@Table(name = "info_paid")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InfoPaid implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "expiry_date")
    private Instant expiryDate;

    @ManyToOne
    @JsonIgnoreProperties(value = { "payIds", "groups", "infoPaids" }, allowSetters = true)
    private TelegramAccount accId;

    @ManyToOne
    @JsonIgnoreProperties(value = { "payIds", "infoPaids" }, allowSetters = true)
    private Period periodId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public InfoPaid id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getExpiryDate() {
        return this.expiryDate;
    }

    public InfoPaid expiryDate(Instant expiryDate) {
        this.setExpiryDate(expiryDate);
        return this;
    }

    public void setExpiryDate(Instant expiryDate) {
        this.expiryDate = expiryDate;
    }

    public TelegramAccount getAccId() {
        return this.accId;
    }

    public void setAccId(TelegramAccount telegramAccount) {
        this.accId = telegramAccount;
    }

    public InfoPaid accId(TelegramAccount telegramAccount) {
        this.setAccId(telegramAccount);
        return this;
    }

    public Period getPeriodId() {
        return this.periodId;
    }

    public void setPeriodId(Period period) {
        this.periodId = period;
    }

    public InfoPaid periodId(Period period) {
        this.setPeriodId(period);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InfoPaid)) {
            return false;
        }
        return id != null && id.equals(((InfoPaid) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InfoPaid{" +
            "id=" + getId() +
            ", expiryDate='" + getExpiryDate() + "'" +
            "}";
    }
}
