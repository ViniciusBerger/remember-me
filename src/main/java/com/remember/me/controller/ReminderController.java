package com.remember.me.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.management.RuntimeErrorException;
import com.remember.me.dto.ReminderRequestDTO;
import com.remember.me.dto.ReminderResponseDTO;
import com.remember.me.model.Reminder;
import com.remember.me.service.ReminderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/reminders")
public class ReminderController {
    
    //setup variable for spring injection
    private final ReminderService reminderService;

    // constructor
    public ReminderController(ReminderService reminderService) {
        this.reminderService = reminderService;
    }

    // handle post requests for /reminders
    @PostMapping
    public Map<String, String> addReminder(@RequestBody ReminderRequestDTO request) {
        try {

            Reminder newReminder = new Reminder(request.getTitle(), request.getReminderTime());
            Map<String,String> res = new HashMap<>();
            
            //actually add the reminder
            Reminder reminder = reminderService.addReminder(newReminder);
            
            //build up response
            res.put("status", "200");
            res.put("message", reminder.toString());

            return res;
        } catch (Error e) {
            throw new RuntimeErrorException(e ,"deu errado no add reminder");
        }

    }

    
    // handle get requests for /reminders
    @GetMapping
    public Map<String, Object> getReminders() {
        try {
            Map<String,Object> res = new HashMap<>();

            List<ReminderResponseDTO> remindersFromDb = reminderService.GetReminders();

            res.put("status", "200");
            res.put("data", remindersFromDb);

            return res;
        } catch (Error e) {
            throw new RuntimeErrorException(e ,"deu errado no get reminder");
        }
    }
}
