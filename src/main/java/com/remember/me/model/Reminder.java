package com.remember.me.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
public class Reminder {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String title;
    private LocalTime reminderTime;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Reminder(){

    };

    public Reminder(String title, LocalTime reminderTime){
        this.title = title;
        this.reminderTime = reminderTime;
    };

    public String toString(){
        return "Title: " + title + "\n" + "reminderTime: " + reminderTime + "\n" + "createdAt: " + createdAt + "\n" + "updatedAt: " + updatedAt;
    }

    public long getId() {
        return id;
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



}
