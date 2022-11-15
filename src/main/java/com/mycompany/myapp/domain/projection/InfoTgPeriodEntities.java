package com.mycompany.myapp.domain.projection;

import com.mycompany.myapp.domain.enumeration.DatesOfPeriod;
import com.mycompany.myapp.domain.enumeration.Role;
import java.time.Instant;

public interface InfoTgPeriodEntities {
    Long getInfoId();
    Instant getExpiry();
    Long getAccId();
    Long getPeriodId();
    Long getTgId();
    Long getChatId();
    String getUsername();
    String getFirstname();
    String getLastname();
    String getPhone();
    Role getRole();
    Instant getTgCreateAt();
    Long getGroupsId();
    Long getPdId();
    String getPeriodName();
    Instant getPdCreateAt();
    Double getAmount();
    DatesOfPeriod getDatesOfPeriod();
    Long getPayId();
    Double getPayAmount();
    Boolean getPayIsPaid();
    Instant getPayPaidAt();
    Instant getPayExpiryDate();
    Long getPayAccId();
    Long getPayPeriodId();
}
