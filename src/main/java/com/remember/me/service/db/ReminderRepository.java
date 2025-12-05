package com.remember.me.service.db;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.remember.me.model.Reminder;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {

    Optional<Reminder> findByNameContainingIgnoreCase(String name);
}
