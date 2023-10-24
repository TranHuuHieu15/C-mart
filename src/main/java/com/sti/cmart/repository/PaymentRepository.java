package com.sti.cmart.repository;

import com.sti.cmart.entity.Chat;
import com.sti.cmart.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByName(String name);
}
