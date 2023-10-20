package com.sti.cmart.repository;

import com.sti.cmart.entity.Chat;
import com.sti.cmart.entity.EWallet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EWalletRepository extends JpaRepository<EWallet, Long> {
    Optional<EWallet> findByBank (String bank);

    Page<EWallet> findAllByStatus(Short status, Pageable pageable);

    Page<EWallet> findAllByBank(String bank, Pageable pageable);

    Page<EWallet> findAll(Pageable pageable);
}
