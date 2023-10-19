package com.sti.cmart.repository;

import com.sti.cmart.entity.Service;
import com.sti.cmart.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
