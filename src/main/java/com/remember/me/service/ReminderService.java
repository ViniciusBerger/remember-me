package com.remember.me.service;


import java.util.List;
import java.util.stream.Collectors;
import javax.management.RuntimeErrorException;
import org.springframework.stereotype.Service;
import com.remember.me.dto.ReminderResponseDTO;
import com.remember.me.model.Reminder;
import com.remember.me.service.db.ReminderRepository;

@Service
public class ReminderService implements IReminderService{

    //Spring inject the database
    private final ReminderRepository reminderRepository;

    //constructor
    public ReminderService(ReminderRepository reminderRepository){
        this.reminderRepository = reminderRepository;
    }


    @Override
    public Reminder addReminder(Reminder reminder) {
        try{
            // save on database
            Reminder reminderFromDb = reminderRepository.save(reminder);
            
            // return reminder to controller
            return reminderFromDb; 
        }
        catch(Error e) {
            throw new RuntimeErrorException(e, e.getMessage()); 
        }
    }


    @Override
    public List<ReminderResponseDTO> GetReminders() {
        try {
            // get reminders from db and convert into DTO model
            List<ReminderResponseDTO> remindersFromDb = reminderRepository.findAll().stream()
                                                                                    .map(ReminderResponseDTO::toReminderResponse)
                                                                                    .collect(Collectors.toList());

            return remindersFromDb;
        } catch(Error e) {
             // TODO Auto-generated method stub
            throw new RuntimeErrorException(e, e.getMessage());
        }
    }

    


    @Override
    public String editReminder() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editReminder'");
    }


    @Override
    public String deleteReminder() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteReminder'");
    }
    
}
