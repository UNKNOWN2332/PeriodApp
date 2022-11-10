package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Groups;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Groups entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GroupsRepository extends JpaRepository<Groups, Long> {}
