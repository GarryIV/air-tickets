package com.garryiv.air_tickets.core.services.notification.queue;

import com.garryiv.air_tickets.core.services.CoreServiceTest;
import com.garryiv.air_tickets.core.services.notification.email.Email;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@CoreServiceTest
public class NotificationServiceTest {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private NotificationRepository notificationRepository;

    @Test
    public void add() throws Exception {
        Email email = new Email("to", "subj", new byte[] {33}, Collections.emptyList());
        Long id = notificationService.add(email);

        Notification notification = notificationRepository.findOne(id);
        assertNotNull(notification);

        Email fromDb = notification.getPayload();

        // it's required to test persistence
        assertNotSame(email, fromDb);

        assertEquals(email.getRecipient(), fromDb.getRecipient());
        assertEquals(email.getSubject(), fromDb.getSubject());
        assertArrayEquals(email.getBody(), fromDb.getBody());
    }

}