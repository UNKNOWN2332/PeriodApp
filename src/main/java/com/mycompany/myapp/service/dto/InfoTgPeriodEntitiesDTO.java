package com.mycompany.myapp.service.dto;

import com.mycompany.myapp.domain.enumeration.DatesOfPeriod;
import com.mycompany.myapp.domain.enumeration.Role;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InfoTgPeriodEntitiesDTO {

    private Long infoId;
    private Instant expiry;
    private Long accId;
    private Long periodId;
    private Long tgId;
    private Long chatId;
    private String username;
    private String firstname;
    private String lastname;
    private String phone;
    private Role role;
    private Instant tgCreateAt;
    private Long groupsId;
    private Long pdId;
    private String periodName;
    private Instant pdCreateAt;
    private Double amount;
    private DatesOfPeriod datesOfPeriod;
    private Long payId;
    private Double payAmount;
    private Boolean payIsPaid;
    private Instant payPaidAt;
    private Instant payExpiryDate;
    private Long payAccId;
    private Long payPeriodId;
    private Double residual = 0D;

    public Double getResidual() {
        return getPayAmount() == null ? getAmount() : getAmount() - getPayAmount();
    }
}
