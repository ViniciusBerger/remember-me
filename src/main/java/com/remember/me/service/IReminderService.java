package com.remember.me.service;

import java.util.List;
import java.util.Optional;

import com.remember.me.dto.ReminderEditRequestDTO;
import com.remember.me.dto.ReminderResponseDTO;
import com.remember.me.model.Reminder;

/**
 * Service interface for managing reminders in the application.
 */
public interface IReminderService {

    /**
     * Creates and saves a new reminder.
     *
     * @return a status message and identifier for the created reminder
     */
    Reminder addReminder(Reminder reminder);

    /**
     * Get all reminders.
     *
     * @return a list of reminders
     */
    List<ReminderResponseDTO> GetAllReminders();

    /**
     * Get one specific reminder.
     *
     * @return a list of reminders
     */
    Optional<ReminderResponseDTO> getReminder(String title);

    /**
     * Updates an existing reminder.
     *
     * @return a status message indicating the result of the update
     */
    ReminderResponseDTO editReminder(Long id, ReminderEditRequestDTO editRequest);

    /**
     * Deletes an existing reminder.
     *
     * @return a status message indicating the result of the deletion
     */
    String deleteReminder();
}
