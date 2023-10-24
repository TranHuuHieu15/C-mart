package com.sti.cmart.repository;

import com.sti.cmart.entity.Vehicle;
import com.sti.cmart.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Long> {
    Optional<Voucher> findByName(String name);
}
