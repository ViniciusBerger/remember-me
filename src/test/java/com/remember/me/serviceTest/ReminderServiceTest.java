package com.remember.me.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.remember.me.dto.ReminderEditRequestDTO;
import com.remember.me.dto.ReminderResponseDTO;
import com.remember.me.model.Reminder;
import com.remember.me.service.ReminderService;
import com.remember.me.service.db.ReminderRepository;

@ExtendWith(MockitoExtension.class)
public class ReminderServiceTest {
    
    @Mock
    private ReminderRepository reminderRepository;

    @InjectMocks
    private ReminderService reminderService;


    @Test
    void addReminder_shouldReturn_reminder(){
        Reminder expectedReminder = new Reminder("test", LocalTime.of(10, 0));
        
        // mock database repository 
        when(reminderRepository.save(expectedReminder))
            .thenReturn(expectedReminder);

        // test method
        Reminder reminderAdded = reminderService.addReminder(expectedReminder);

        // check response
        assertEquals(expectedReminder, reminderAdded);
    }


    @Test
    void getAllReminder_shouldReturn_listOfReminders() {
        Reminder r1 = new Reminder("test1", LocalTime.of(11,0));
        Reminder r2 = new Reminder("test1", LocalTime.of(12,0));
        
        List<Reminder> expectedReminders = List.of(r1,r2);
       
        // mock database repository 
        when(reminderRepository.findAll())
            .thenReturn(expectedReminders);

        // test method
        List<ReminderResponseDTO> remindersFromDb = reminderService.GetAllReminders();

        // check response
        assertEquals(expectedReminders.size(), remindersFromDb.size());
        assertEquals(expectedReminders.get(0).getTitle(), remindersFromDb.get(0).getTitle());
        assertEquals(expectedReminders.get(1).getTitle(), remindersFromDb.get(1).getTitle());
    }


    @Test
    void getOneReminder_shouldReturn_reminder() {
        Reminder r1 = new Reminder("test1", LocalTime.of(11,0));

        // mock database call
        when(reminderRepository.findByNameContainingIgnoreCase(r1.getTitle()))
            .thenReturn(Optional.of(r1));

        // test method
        ReminderResponseDTO expectedReminder = reminderService.getReminder(r1.getTitle()).get();

        // check response
        assertEquals(expectedReminder.getTitle(), r1.getTitle());
        assertEquals(expectedReminder.getReminderTime(), r1.getReminderTime());
        verify(reminderRepository).findByNameContainingIgnoreCase(r1.getTitle());
    }


    @Test
    void getOneReminder_shouldReturn_emptyOptional() {
        String searchTitle = "some random title";

        // mock database call
        when(reminderRepository.findByNameContainingIgnoreCase(searchTitle))
            .thenReturn(Optional.empty());

        // test method
        Optional<ReminderResponseDTO> expectedReminder = reminderService.getReminder(searchTitle);

        // check response
        assertTrue(expectedReminder.isEmpty()); 
        verify(reminderRepository).findByNameContainingIgnoreCase(searchTitle);
    }


    @Test
    void editReminder_shouldReturn_newVersionOfReminder(){

        Reminder reminder = new Reminder("test", LocalTime.of(10,0));        
        ReminderEditRequestDTO editRequest = new ReminderEditRequestDTO("new testing title", LocalTime.of(9, 30));

        // mock finding by id
        when(reminderRepository.findById(01L)).thenReturn(Optional.of(reminder));

        // mock saving
        when(reminderRepository.save(any(Reminder.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // testing method
        ReminderResponseDTO newReminder = reminderService.editReminder(01L, editRequest);

        // check response
        assertEquals(editRequest.getTitle(), newReminder.getTitle());
        assertEquals(editRequest.getReminderTime(), newReminder.getReminderTime());

    }
    
}
