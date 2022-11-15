package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.InfoPaid;
import com.mycompany.myapp.domain.projection.InfoTgPeriodEntities;
import com.mycompany.myapp.domain.projection.NextPayProjection;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
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
        value = "select tg.id as tgId,\n" +
        "       p.id as periodId,\n" +
        "       p2.amount as payAmount ,\n" +
        "       p.amount  as periodAmount\n" +
        "from info_paid inf\n" +
        "    join period p on inf.period_id_id = p.id\n" +
        "    join telegram_account tg on tg.id =inf.acc_id_id\n" +
        "    left join pay p2 on inf.last_pay_id = p2.id\n" +
        "    where inf.acc_id_id=?\n",
        nativeQuery = true
    )
    List<NextPayProjection> findByAccIdUZB(Long accId);

    @Query(
        value = "select p3.id as infoId,\n" +
        "        p3.expiry_date as expiry,\n" +
        "        p3.acc_id_id as accId,\n" +
        "        p3.period_id_id as periodId,\n" +
        "        ta.id as tgId,\n" +
        "        ta.chat_id as chatId,\n" +
        "        ta.username,\n" +
        "        ta.firstname,\n" +
        "        ta.lastname,\n" +
        "        ta.phone_number as phone,\n" +
        "        ta.role,\n" +
        "        ta.create_at as tgCreateAt,\n" +
        "        ta.groups_id as groupsId,\n" +
        "        p.id as pdId,\n" +
        "        p.period_name as periodName,\n" +
        "        p.create_at as pdCreateAt,\n" +
        "        p.amount,\n" +
        "        p.dates_of_period as datesOfperiod,\n" +
        "        pa.id as payId,\n" +
        "        pa.amount as payAmount,\n" +
        "        pa.is_paid as payIsPaid,\n" +
        "        pa.paid_at as payPaidAt,\n" +
        "        pa.expiry_date as payExpiryDate,\n" +
        "        pa.acc_id_id as payAccId,\n" +
        "        pa.period_id_id as payPeriodId\n" +
        "                from info_paid p3\n" +
        "               inner join telegram_account ta on p3.acc_id_id = ta.id\n" +
        "               inner join period p  on p3.period_id_id = p.id\n" +
        "               left join pay pa  on pa.id = p3.last_pay_id\n" +
        "                where p3.expiry_date < ?",
        nativeQuery = true
    )
    List<InfoTgPeriodEntities> getAllDebtTelegramAccount(Instant toDate);
}
