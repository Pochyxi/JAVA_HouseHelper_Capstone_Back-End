package com.adiener.java_househelper_capstone_backend.repositories;

import com.adiener.java_househelper_capstone_backend.Entities.Bolletta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface BollettaRepository extends JpaRepository<Bolletta, Long> {
    @Query(
            "select b from Bolletta b where b.user.id = :userId"
    )
    public List<Bolletta> findBollettaByUserId( Long userId );
}
