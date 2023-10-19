package com.sti.cmart.repository;

import com.sti.cmart.entity.Chat;
import com.sti.cmart.entity.EWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EWalletRepository extends JpaRepository<EWallet, Long> {
}
