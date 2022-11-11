package com.mycompany.myapp.service.schedule;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.myapp.repository.InfoPaidRepository;
import com.mycompany.myapp.repository.PeriodRepository;
import com.mycompany.myapp.repository.TelegramAccountRepository;
import java.time.Instant;
import javax.annotation.PostConstruct;
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

    @Scheduled(cron = "9 * * * * *")
    public boolean selectAllQarziBorTgAccounts() {
        Instant a = Instant.now().plusSeconds(18_000);
        System.out.println(a);
        var info = infoPaidRepository.getAllDebtTelegramAccount(a);
        try {
            System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(info));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
