package com.mycompany.myapp.service.schedule;

import com.mycompany.myapp.repository.InfoPaidRepository;
import com.mycompany.myapp.repository.PeriodRepository;
import com.mycompany.myapp.repository.TelegramAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@RequiredArgsConstructor
@EnableScheduling
@Configuration
public class Schedule {

    private final InfoPaidRepository infoPaidRepository;

    private final TelegramAccountRepository telegramAccountRepository;

    private final PeriodRepository periodRepository;

    @Scheduled(cron = "* * * * * *")
    public boolean selectAllQarziBorTgAccounts() {
        return true;
    }
}
