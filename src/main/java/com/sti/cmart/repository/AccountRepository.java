package com.sti.cmart.repository;

import com.sti.cmart.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByEmail(String email);
    Optional<Account> findByPhone(String phone);
    Page<Account> findAllByFullname(String fullname, Pageable pageable);
    Optional<Account> findByUsername(String username);
    Boolean existsByUsername(String username);

}
