package com.adiener.java_househelper_capstone_backend.repositories;

import com.adiener.java_househelper_capstone_backend.Entities.ListaSpesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListaSpesaRepository extends JpaRepository<ListaSpesa, Long> {

    @Query(
            "select l from ListaSpesa l where l.user.id = :userId"
    )
    public List<ListaSpesa> findListaSpesaByUserId( Long userId );
}
