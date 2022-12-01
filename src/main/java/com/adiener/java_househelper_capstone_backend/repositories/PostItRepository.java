package com.adiener.java_househelper_capstone_backend.repositories;

import com.adiener.java_househelper_capstone_backend.Entities.PostIt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostItRepository extends JpaRepository<PostIt, Long> {

    @Query(
            "select p from PostIt p where p.user.id = :userId order by p.scadenza"
    )
    public List<PostIt> findPostItByUserId( @Param( "userId" ) Long userId );
}
