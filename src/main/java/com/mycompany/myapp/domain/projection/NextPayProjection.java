package com.mycompany.myapp.domain.projection;

public interface NextPayProjection {
    Long getTgId();
    Long getPeriodId();
    Double getPayAmount();
    Double getPeriodAmount();
}
