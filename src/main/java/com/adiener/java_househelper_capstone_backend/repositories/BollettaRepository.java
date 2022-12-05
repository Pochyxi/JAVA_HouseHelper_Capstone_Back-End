package com.adiener.java_househelper_capstone_backend.repositories;

import com.adiener.java_househelper_capstone_backend.Entities.Bolletta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;

@Repository
public interface BollettaRepository extends JpaRepository<Bolletta, Long> {
    @Query(
            "select b from Bolletta b where b.user.id = :userId"
    )
    List<Bolletta> findBollettaByUserId( Long userId );

    @Query(
            "select b from Bolletta b where b.emissione >=:inizio and b.emissione <=:fine and b.user.id = :userId"
    )
    List<Bolletta> findBollettaByEmissioneRange( @Param("inizio") LocalDate inizio,
                                                        @Param("fine") LocalDate fine, @Param("userId") Long userId );

    @Query(
            "select b from Bolletta b where b.numero >=:numero and b.user.id = :userId"
    )
    List<Bolletta>  findBollettaByNumero( Long numero, Long userId );

    @Query(
            "select b from Bolletta b where b.scadenza >=:scadenzaInizio and b.scadenza <=:scadenzaFine and b.user" +
                    ".id=:userId"
    )
    List<Bolletta> findBollettaByScadenzaRange(@Param( "scadenzaInizio" ) LocalDate scadenzaInizio,
                                          @Param( "scadenzaFine" ) LocalDate scadenzaFine,
                                          @Param( "userId" ) Long userId);
}
