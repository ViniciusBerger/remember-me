package com.remember.me.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

import com.remember.me.model.Reminder;

public class ReminderResponseDTO {
    private String title;
    private LocalTime reminderTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ReminderResponseDTO(String title, LocalTime reminderTime, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.title = title;
        this.reminderTime = reminderTime;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static ReminderResponseDTO toReminderResponseDTO(Reminder r) {
        return new ReminderResponseDTO(
                r.getTitle(),
                r.getReminderTime(),
                r.getCreatedAt(),
                r.getUpdatedAt()
        );
    }

    
    public String getTitle(){
        return this.title;
    }

    public LocalTime getReminderTime(){
        return this.reminderTime;
    }

    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }

    public LocalDateTime getUpdatedAt(){
        return this.updatedAt;
    }
}
