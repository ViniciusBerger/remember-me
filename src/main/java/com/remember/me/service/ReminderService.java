package com.remember.me.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.management.RuntimeErrorException;
import org.springframework.stereotype.Service;

import com.remember.me.dto.ReminderEditRequestDTO;
import com.remember.me.dto.ReminderResponseDTO;
import com.remember.me.model.Reminder;
import com.remember.me.service.db.ReminderRepository;

import jakarta.transaction.Transactional;

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
        // save on database
        Reminder reminderFromDb = reminderRepository.save(reminder);
            
        // return reminder to controller
        return reminderFromDb; 

    }


    @Override
    public List<ReminderResponseDTO> GetAllReminders() {
        
        // get reminders from db and convert into DTO model
        List<ReminderResponseDTO> remindersFromDb = reminderRepository.findAll()
                                                                            .stream()
                                                                            .map(ReminderResponseDTO::toReminderResponseDTO)
                                                                            .collect(Collectors.toList());

        //return reminder as DTO object
        return remindersFromDb;
    }

    @Override
    public Optional<ReminderResponseDTO> getReminder(String title){
        // find reminder in database
        Optional<Reminder> reminderFromDb = reminderRepository.findByNameContainingIgnoreCase(title);

        // parse it to DTO 
        Optional<ReminderResponseDTO> response = reminderFromDb.map(ReminderResponseDTO::toReminderResponseDTO);
        
        // return
        return response;
    }

    
    @Override
    @Transactional
    public ReminderResponseDTO editReminder(Long id, ReminderEditRequestDTO editRequest) {

        Reminder reminder = reminderRepository.findById(id).orElseThrow(()-> new RuntimeException("Reminder not found"));

        reminder.setTitle(editRequest.getTitle());
        reminder.setReminderTime(editRequest.getReminderTime());

        reminderRepository.save(reminder);
       
        return ReminderResponseDTO.toReminderResponseDTO(reminder);
    }


    @Override
    public String deleteReminder() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteReminder'");
    }
    
}
