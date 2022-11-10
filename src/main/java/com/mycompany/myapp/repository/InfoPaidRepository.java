package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.InfoPaid;
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
}
