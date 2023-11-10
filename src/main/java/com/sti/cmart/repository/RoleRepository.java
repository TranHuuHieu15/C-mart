package com.sti.cmart.repository;

import com.sti.cmart.entity.Account;
import com.sti.cmart.entity.Role;
import com.sti.cmart.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(RoleName roleName);

//    Optional<Role> findTopByRoleName(RoleName roleName);

    boolean existsByRoleName(RoleName roleName);

    List<Role> findAllByAccountUsername(String username);

    @Query("select r from Role r where r.account.id = ?1")
    Role findByAccount_Id(Long id);

}
