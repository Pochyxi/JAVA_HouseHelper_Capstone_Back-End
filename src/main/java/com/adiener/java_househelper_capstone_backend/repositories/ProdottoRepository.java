package com.adiener.java_househelper_capstone_backend.repositories;

import com.adiener.java_househelper_capstone_backend.Entities.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {
    @Query(
            value = "select p from Prodotto p where p.user.id = :id"
    )
    List<Prodotto> findProdottoByUserId( @Param ( "id" ) Long id );
}
