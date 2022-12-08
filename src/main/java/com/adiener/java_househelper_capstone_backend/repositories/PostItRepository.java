package com.adiener.java_househelper_capstone_backend.repositories;

import com.adiener.java_househelper_capstone_backend.Entities.Bolletta;
import com.adiener.java_househelper_capstone_backend.Entities.PostIt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostItRepository extends JpaRepository<PostIt, Long> {

    @Query(
            "select p from PostIt p where p.user.id = :userId order by p.scadenza"
    )
    public List<PostIt> findPostItByUserId( @Param( "userId" ) Long userId );

    @Query(
            "select p from PostIt p where p.scadenza >=:scadenzaInizio and p.scadenza <=:scadenzaFine and p.user" +
                    ".id=:userId and p.stato=false"
    )
    List<PostIt> findPostItByScadenzaRange( @Param( "scadenzaInizio" ) LocalDate scadenzaInizio,
                                                @Param( "scadenzaFine" ) LocalDate scadenzaFine,
                                                @Param( "userId" ) Long userId);
}
