package com.sti.cmart.repository;

import com.sti.cmart.entity.Role;
import com.sti.cmart.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(RoleName roleName);

//    Optional<Role> findTopByRoleName(RoleName roleName);

    boolean existsByRoleName(RoleName roleName);
}
