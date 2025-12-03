package com.remember.me.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalTime;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

        // call method being tested
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

         // call method being tested
        List<ReminderResponseDTO> remindersFromDb = reminderService.GetReminders();

        // check response
        assertEquals(expectedReminders.size(), remindersFromDb.size());
        assertEquals(expectedReminders.get(0).getTitle(), remindersFromDb.get(0).getTitle());
        assertEquals(expectedReminders.get(1).getTitle(), remindersFromDb.get(1).getTitle());
    }
    
}
