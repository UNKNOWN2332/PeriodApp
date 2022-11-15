package com.mycompany.myapp.service.schedule;

import static com.mycompany.myapp.service.mapper.InfoTgPeriodMapper.toDto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.myapp.repository.InfoPaidRepository;
import com.mycompany.myapp.service.mapper.InfoTgPeriodMapper;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@RequiredArgsConstructor
@EnableScheduling
@Configuration
public class Schedule {

    private final InfoPaidRepository infoPaidRepository;

    private final ObjectMapper objectMapper;

    @Scheduled(cron = "* * * * * *")
    public boolean selectAllDeptTgAccounts() {
        Instant a = Instant.now().plusSeconds(18_000);
        var info = infoPaidRepository.getAllDebtTelegramAccount(a);
        var infoDto = info.stream().map(InfoTgPeriodMapper::toDto);
        try {
            System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(infoDto));
            return true;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
