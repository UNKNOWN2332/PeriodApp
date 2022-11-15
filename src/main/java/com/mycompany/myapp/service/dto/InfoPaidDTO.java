package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.InfoPaid} entity.
 */
public class InfoPaidDTO implements Serializable {

    private Long id;

    private Instant expiryDate;

    private Long lastPayId;

    private TelegramAccountDTO accId;

    private PeriodDTO periodId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Instant expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Long getLastPayId() {
        return lastPayId;
    }

    public void setLastPayId(Long lastPayId) {
        this.lastPayId = lastPayId;
    }

    public TelegramAccountDTO getAccId() {
        return accId;
    }

    public void setAccId(TelegramAccountDTO accId) {
        this.accId = accId;
    }

    public PeriodDTO getPeriodId() {
        return periodId;
    }

    public void setPeriodId(PeriodDTO periodId) {
        this.periodId = periodId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InfoPaidDTO)) {
            return false;
        }

        InfoPaidDTO infoPaidDTO = (InfoPaidDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, infoPaidDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InfoPaidDTO{" +
            "id=" + getId() +
            ", expiryDate='" + getExpiryDate() + "'" +
            ", lastPayId=" + getLastPayId() +
            ", accId=" + getAccId() +
            ", periodId=" + getPeriodId() +
            "}";
    }
}
