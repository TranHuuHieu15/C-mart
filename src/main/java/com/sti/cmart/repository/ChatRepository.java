package com.sti.cmart.repository;

import com.sti.cmart.entity.Address;
import com.sti.cmart.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
}
