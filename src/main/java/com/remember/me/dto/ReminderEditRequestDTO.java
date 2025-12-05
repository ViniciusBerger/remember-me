package com.remember.me.dto;

import java.time.LocalTime;

public class ReminderEditRequestDTO {
    private String title;
    private LocalTime reminderTime;
    
    public ReminderEditRequestDTO(String title, LocalTime reminderTime) {
        this.title = title;
        this.reminderTime = reminderTime;
    }


    public String getTitle(){
        return this.title;
    }

    public LocalTime getReminderTime(){
        return this.reminderTime;
    }
}
