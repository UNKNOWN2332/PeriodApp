package com.mycompany.myapp.service.helper;

import com.mycompany.myapp.domain.Pay;
import com.mycompany.myapp.domain.Period;
import com.mycompany.myapp.domain.TelegramAccount;
import com.mycompany.myapp.service.dto.IsPaidDTO;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class PayHelper {

    public static String isEmptyDo() {
        return "";
    }

    public static List<Pay> collection(IsPaidDTO isPaidDTO, Period period, TelegramAccount account, Instant day) {
        List<Pay> pays = new ArrayList<>();
        Instant a = day;
        for (int i = 1; i <= isPaidDTO.getAmount() / period.getAmount(); i++) {
            Pay pay = new Pay();
            pay.setPeriodId(period);
            pay.setPaidAt(Instant.now());
            pay.setAccId(account);
            pay.setAmount(period.getAmount());
            pay.setExpiryDate(day.plusSeconds(period.getDatesOfPeriod().getSecond() * i));
            pay.setIsPaid(true);
            a = pay.getExpiryDate();
            pays.add(pay);
        }
        if (isPaidDTO.getAmount() % period.getAmount() == 0) return pays;
        Pay pay = new Pay();
        pay.setPeriodId(period);
        pay.setPaidAt(Instant.now());
        pay.setAccId(account);
        pay.setAmount(isPaidDTO.getAmount() % period.getAmount());
        pay.setIsPaid(false);
        pay.setExpiryDate(a);
        pays.add(pay);
        return pays;
    }
}
