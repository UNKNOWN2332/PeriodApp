package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Pay;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Pay entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PayRepository extends JpaRepository<Pay, Long> {
    @Query(
        value = "select * from pay where (amount, expiry_date) =  (select max(amount) ,max(expiry_date) from pay where acc_id_id=? and period_id_id=? and is_paid =false limit 1)limit 1",
        nativeQuery = true
    )
    Optional<Pay> findTop1ByAccIdAndPeriodsId(Long telegramAccountId, Long periodId);

    @Query(
        value = "select * from pay where (amount, expiry_date) =  (select max(amount) ,max(expiry_date) from pay where acc_id_id=? and period_id_id=? and is_paid =true limit 1)limit 1",
        nativeQuery = true
    )
    Optional<Pay> findTop1ByAccIdAndPeriodsIdre(Long telegramAccountId, Long periodId);
}
