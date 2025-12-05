package com.remember.me.model;

import jakarta.persistence.Column;
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
    @Column(unique = true)
    private String title;
    private LocalTime reminderTime;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;


    // constructors 
    public Reminder(){};
    public Reminder(String title, LocalTime reminderTime){
        this.title = title;
        this.reminderTime = reminderTime;
    };
    
    // title section
    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }


    // reminderTime section
    public LocalTime getReminderTime(){
        return this.reminderTime;
    }
    public void setReminderTime(LocalTime newTime){
        this.reminderTime = newTime;
    }

    /*
     *  Getters
     */
    public long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }

    public LocalDateTime getUpdatedAt(){
        return this.createdAt;
    }

    public String toString(){
        return "Title: " + title + "\n" + "reminderTime: " + reminderTime + "\n" + "createdAt: " + createdAt + "\n" + "updatedAt: " + updatedAt;
    }

}
