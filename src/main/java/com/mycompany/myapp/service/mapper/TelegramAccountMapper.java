package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Groups;
import com.mycompany.myapp.domain.TelegramAccount;
import com.mycompany.myapp.service.dto.GroupsDTO;
import com.mycompany.myapp.service.dto.TelegramAccountDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TelegramAccount} and its DTO {@link TelegramAccountDTO}.
 */
@Mapper(componentModel = "spring")
public interface TelegramAccountMapper extends EntityMapper<TelegramAccountDTO, TelegramAccount> {
    @Mapping(target = "groups", source = "groups", qualifiedByName = "groupsId")
    TelegramAccountDTO toDto(TelegramAccount s);

    @Named("groupsId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    GroupsDTO toDtoGroupsId(Groups groups);
}
