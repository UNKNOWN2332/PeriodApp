package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Period;
import com.mycompany.myapp.service.dto.PeriodDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Period} and its DTO {@link PeriodDTO}.
 */
@Mapper(componentModel = "spring")
public interface PeriodMapper extends EntityMapper<PeriodDTO, Period> {}
