package com.snwm.englishbot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.snwm.englishbot.entity.ReminderMessage;

public interface ReminderMessageRepository extends JpaRepository<ReminderMessage, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM reminder_message ORDER BY RANDOM() LIMIT 1")
    Optional<ReminderMessage> findRandom();
}
