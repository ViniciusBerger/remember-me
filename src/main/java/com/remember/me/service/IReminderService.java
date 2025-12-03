package com.remember.me.service;

import java.util.List;

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
    List<ReminderResponseDTO> GetReminders();

    /**
     * Updates an existing reminder.
     *
     * @return a status message indicating the result of the update
     */
    String editReminder();

    /**
     * Deletes an existing reminder.
     *
     * @return a status message indicating the result of the deletion
     */
    String deleteReminder();
}
