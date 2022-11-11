package com.mycompany.myapp.service.impl;

import static com.mycompany.myapp.service.helper.PayHelper.*;

import com.mycompany.myapp.domain.InfoPaid;
import com.mycompany.myapp.domain.Pay;
import com.mycompany.myapp.domain.Period;
import com.mycompany.myapp.domain.TelegramAccount;
import com.mycompany.myapp.repository.InfoPaidRepository;
import com.mycompany.myapp.repository.PayRepository;
import com.mycompany.myapp.repository.PeriodRepository;
import com.mycompany.myapp.repository.TelegramAccountRepository;
import com.mycompany.myapp.service.PayService;
import com.mycompany.myapp.service.dto.IsPaidDTO;
import com.mycompany.myapp.service.dto.PayDTO;
import com.mycompany.myapp.service.dto.ResponsDTO;
import com.mycompany.myapp.service.mapper.PayMapper;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Service Implementation for managing {@link Pay}.
 */
@Service
@Transactional
public class PayServiceImpl implements PayService {

    private final Logger log = LoggerFactory.getLogger(PayServiceImpl.class);

    private final PayRepository payRepository;

    private final PayMapper payMapper;

    private final PeriodRepository periodRepository;

    private final TelegramAccountRepository telegramAccountRepository;

    private final InfoPaidRepository infoPaidRepository;

    public PayServiceImpl(
        PayRepository payRepository,
        PayMapper payMapper,
        PeriodRepository periodRepository,
        TelegramAccountRepository telegramAccountRepository,
        InfoPaidRepository infoPaidRepository
    ) {
        this.payRepository = payRepository;
        this.payMapper = payMapper;
        this.periodRepository = periodRepository;
        this.telegramAccountRepository = telegramAccountRepository;
        this.infoPaidRepository = infoPaidRepository;
    }

    @Override
    public PayDTO save(PayDTO payDTO) {
        log.debug("Request to save Pay : {}", payDTO);
        Pay pay = payMapper.toEntity(payDTO);
        pay = payRepository.save(pay);
        return payMapper.toDto(pay);
    }

    @Override
    public PayDTO update(PayDTO payDTO) {
        log.debug("Request to save Pay : {}", payDTO);
        Pay pay = payMapper.toEntity(payDTO);
        pay = payRepository.save(pay);
        return payMapper.toDto(pay);
    }

    @Override
    public Optional<PayDTO> partialUpdate(PayDTO payDTO) {
        log.debug("Request to partially update Pay : {}", payDTO);

        return payRepository
            .findById(payDTO.getId())
            .map(existingPay -> {
                payMapper.partialUpdate(existingPay, payDTO);

                return existingPay;
            })
            .map(payRepository::save)
            .map(payMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PayDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Pays");
        return payRepository.findAll(pageable).map(payMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PayDTO> findOne(Long id) {
        log.debug("Request to get Pay : {}", id);
        return payRepository.findById(id).map(payMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Pay : {}", id);
        payRepository.deleteById(id);
    }

    @Override
    public ResponsDTO<String> isPaid(IsPaidDTO isPaidDTO) {
        var tgAccount = telegramAccountRepository.findById(isPaidDTO.getAccId()).orElse(null);
        if (tgAccount == null) return new ResponsDTO<>(false, "Telegram Account idn ot found", null);

        var period = periodRepository.findById(isPaidDTO.getPeriodId()).orElse(null);

        if (period == null) return new ResponsDTO<>(false, "Period id not found", null);

        var infoPaid = infoPaidRepository.findByAccIdAndPeriodId(isPaidDTO.getAccId(), isPaidDTO.getPeriodId()).orElse(null);

        if (infoPaid == null) return new ResponsDTO<>(
            false,
            "This account id and period id is not found or you did not pay this period!!",
            null
        );

        var payOptional = payRepository.findTop1ByAccIdAndPeriodsId(isPaidDTO.getAccId(), isPaidDTO.getPeriodId()).orElse(null);

        if (payOptional == null) {
            if (isPaidDTO.getAmount() >= period.getAmount()) {
                List<Pay> resultPays = collection(isPaidDTO, period, tgAccount, infoPaid.getExpiryDate());
                infoPaid.setExpiryDate(resultPays.get(resultPays.size() - 1).getExpiryDate());
                payRepository.saveAll(resultPays);
                infoPaidRepository.save(infoPaid);
                return new ResponsDTO<>(true, "Ok", "Save");
            }

            Pay pay = new Pay();
            pay.setExpiryDate(infoPaid.getExpiryDate());
            pay.setPeriodId(period);
            pay.setPaidAt(Instant.now());
            pay.setAccId(tgAccount);
            pay.setAmount(isPaidDTO.getAmount() % period.getAmount());
            pay.setIsPaid(false);
            payRepository.save(pay);

            return new ResponsDTO<>(true, "Ok", "OK just not paid in full. But we will save it to the base");
        }

        if (!payOptional.getIsPaid()) {
            if (payOptional.getAmount() + isPaidDTO.getAmount() < period.getAmount()) {
                payOptional.setAmount(payOptional.getAmount() + isPaidDTO.getAmount());
                payRepository.save(payOptional);
                return new ResponsDTO<>(true, "Ok", "OK just not paid in full. But we will save it to the base1");
            }

            return checkAmounts(payOptional, isPaidDTO, period, infoPaid, tgAccount);
        }

        return new ResponsDTO<>();
    }

    private ResponsDTO<String> checkAmounts(
        Pay payOptional,
        IsPaidDTO isPaidDTO,
        Period period,
        InfoPaid infoPaid,
        TelegramAccount tgAccount
    ) {
        double a = period.getAmount() - payOptional.getAmount();
        payOptional.setAmount(payOptional.getAmount() + a);
        isPaidDTO.setAmount(isPaidDTO.getAmount() - a);
        payOptional.setIsPaid(true);
        payOptional.setExpiryDate(payOptional.getExpiryDate().plusSeconds(period.getDatesOfPeriod().getSecond()));

        var pays = collection(isPaidDTO, period, tgAccount, payOptional.getExpiryDate());
        infoPaid.setExpiryDate(payOptional.getExpiryDate());
        if (pays.size() != 0) infoPaid.setExpiryDate(pays.get(pays.size() - 1).getExpiryDate());
        infoPaidRepository.save(infoPaid);
        payRepository.saveAll(pays);
        return new ResponsDTO<>(true, "OK", "OK");
    }
}
