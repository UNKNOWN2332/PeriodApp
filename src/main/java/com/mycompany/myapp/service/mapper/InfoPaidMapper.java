package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.InfoPaid;
import com.mycompany.myapp.domain.Period;
import com.mycompany.myapp.domain.TelegramAccount;
import com.mycompany.myapp.service.dto.InfoPaidDTO;
import com.mycompany.myapp.service.dto.PeriodDTO;
import com.mycompany.myapp.service.dto.TelegramAccountDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link InfoPaid} and its DTO {@link InfoPaidDTO}.
 */
@Mapper(componentModel = "spring")
public interface InfoPaidMapper extends EntityMapper<InfoPaidDTO, InfoPaid> {
    @Mapping(target = "accId", source = "accId", qualifiedByName = "telegramAccountId")
    @Mapping(target = "periodId", source = "periodId", qualifiedByName = "periodId")
    InfoPaidDTO toDto(InfoPaid s);

    @Named("telegramAccountId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TelegramAccountDTO toDtoTelegramAccountId(TelegramAccount telegramAccount);

    @Named("periodId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    PeriodDTO toDtoPeriodId(Period period);
}
