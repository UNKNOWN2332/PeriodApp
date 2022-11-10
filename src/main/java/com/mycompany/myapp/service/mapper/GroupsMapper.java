package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Groups;
import com.mycompany.myapp.service.dto.GroupsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Groups} and its DTO {@link GroupsDTO}.
 */
@Mapper(componentModel = "spring")
public interface GroupsMapper extends EntityMapper<GroupsDTO, Groups> {}
