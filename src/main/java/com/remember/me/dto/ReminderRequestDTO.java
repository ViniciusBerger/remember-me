package com.remember.me.dto;

import java.time.LocalTime;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;  // if you use it too
public class ReminderRequestDTO {

    @NotBlank
    private String title;
    @NotNull
    private LocalTime reminderTime;

    public ReminderRequestDTO(String title, LocalTime reminderTime){
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
