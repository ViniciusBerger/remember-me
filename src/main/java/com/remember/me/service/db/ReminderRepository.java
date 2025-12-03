package com.remember.me.service.db;

import org.springframework.data.jpa.repository.JpaRepository;

import com.remember.me.model.Reminder;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {

}
