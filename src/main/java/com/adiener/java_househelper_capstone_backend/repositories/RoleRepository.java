package com.adiener.java_househelper_capstone_backend.repositories;


import com.adiener.java_househelper_capstone_backend.Entities.Role;
import com.adiener.java_househelper_capstone_backend.Entities.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByRoleType( RoleType roleType);

}
