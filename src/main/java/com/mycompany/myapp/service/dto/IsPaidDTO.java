package com.mycompany.myapp.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IsPaidDTO {

    private Long accId;

    private Long periodId;

    private Double amount;
}
