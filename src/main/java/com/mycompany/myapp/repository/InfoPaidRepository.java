package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.InfoPaid;
import com.mycompany.myapp.service.dto.InfoTgPeriodEntities;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the InfoPaid entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InfoPaidRepository extends JpaRepository<InfoPaid, Long> {
    @Query(value = "select * from info_paid where acc_id_id = ? and period_id_id=?", nativeQuery = true)
    Optional<InfoPaid> findByAccIdAndPeriodId(Long accId, Long periodId);

    @Query(
        value = "select p3.id as infoId," +
        "p3.expiry_date as expiry," +
        "p3.acc_id_id as accId," +
        "p3.period_id_id as periodId,\n" +
        "ta.id as tgId," +
        "ta.chat_id as chatId," +
        "ta.username," +
        "ta.firstname," +
        "ta.lastname," +
        "ta.phone_number as phone," +
        "ta.role," +
        "ta.create_at as tgCreateAt," +
        "ta.groups_id as groupsId,\n" +
        "p.id as pdId," +
        "p.period_name as periodName," +
        "p.create_at as pdCreateAt," +
        "p.amount," +
        "p.dates_of_period as datesOfperiod\n" +
        "        from info_paid p3\n" +
        "       inner join telegram_account ta on p3.acc_id_id = ta.id\n" +
        "       inner join period p  on p3.period_id_id = p.id\n" +
        "        where p3.expiry_date < ?",
        nativeQuery = true
    )
    List<InfoTgPeriodEntities> getAllDebtTelegramAccount(Instant toDate);
}
