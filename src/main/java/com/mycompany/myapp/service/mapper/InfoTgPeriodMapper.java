package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.projection.InfoTgPeriodEntities;
import com.mycompany.myapp.domain.projection.NextPayProjection;
import com.mycompany.myapp.service.dto.InfoTgPeriodEntitiesDTO;
import com.mycompany.myapp.service.dto.IsPaidDTO;
import java.util.ArrayList;
import java.util.List;

public class InfoTgPeriodMapper {

    public static InfoTgPeriodEntitiesDTO toDto(InfoTgPeriodEntities infoTgPeriodEntities) {
        return new InfoTgPeriodEntitiesDTO()
            .builder()
            .infoId(infoTgPeriodEntities.getInfoId())
            .expiry(infoTgPeriodEntities.getExpiry())
            .accId(infoTgPeriodEntities.getAccId())
            .periodId(infoTgPeriodEntities.getPeriodId())
            .tgId(infoTgPeriodEntities.getTgId())
            .chatId(infoTgPeriodEntities.getChatId())
            .username(infoTgPeriodEntities.getUsername())
            .firstname(infoTgPeriodEntities.getFirstname())
            .lastname(infoTgPeriodEntities.getLastname())
            .phone(infoTgPeriodEntities.getPhone())
            .role(infoTgPeriodEntities.getRole())
            .tgCreateAt(infoTgPeriodEntities.getTgCreateAt())
            .groupsId(infoTgPeriodEntities.getGroupsId())
            .pdId(infoTgPeriodEntities.getPdId())
            .periodName(infoTgPeriodEntities.getPeriodName())
            .pdCreateAt(infoTgPeriodEntities.getPdCreateAt())
            .amount(infoTgPeriodEntities.getAmount())
            .datesOfPeriod(infoTgPeriodEntities.getDatesOfPeriod())
            .payId(infoTgPeriodEntities.getPayId())
            .payAmount(infoTgPeriodEntities.getPayAmount())
            .payIsPaid(infoTgPeriodEntities.getPayIsPaid())
            .payPaidAt(infoTgPeriodEntities.getPayPaidAt())
            .payAccId(infoTgPeriodEntities.getPayAccId())
            .payPeriodId(infoTgPeriodEntities.getPayPeriodId())
            .build();
    }

    public static IsPaidDTO toDtoIsPaid(NextPayProjection nextPayProjection) {
        return nextPayProjection.getPayAmount() == null
            ? new IsPaidDTO()
                .builder()
                .periodId(nextPayProjection.getPeriodId())
                .accId(nextPayProjection.getTgId())
                .amount(nextPayProjection.getPeriodAmount())
                .build()
            : new IsPaidDTO()
                .builder()
                .periodId(nextPayProjection.getPeriodId())
                .accId(nextPayProjection.getTgId())
                .amount(nextPayProjection.getPeriodAmount() - nextPayProjection.getPayAmount())
                .build();
    }
}
