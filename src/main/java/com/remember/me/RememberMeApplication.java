package com.remember.me;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.remember.me.model.Reminder;
import com.remember.me.service.ReminderService;

import java.time.LocalTime;

@SpringBootApplication
public class RememberMeApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(RememberMeApplication.class, args);

        // get the Spring-managed service
        ReminderService reminderService = ctx.getBean(ReminderService.class);

        // TEMP: manual test
        LocalTime time = LocalTime.of(10, 0);
        reminderService.addReminder(new Reminder("make test", time));
    }
}
