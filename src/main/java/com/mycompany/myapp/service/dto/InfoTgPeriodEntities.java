package com.mycompany.myapp.service.dto;

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
}
