package com.garryiv.air_tickets.core.notification.queue;

import com.garryiv.air_tickets.core.services.CoreServiceTest;
import com.garryiv.air_tickets.core.notification.email.Email;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        Email email = newEmail();
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

    @Test
    public void findPendingNotifications() throws Exception {
        Email email = newEmail();
        Long id = notificationService.add(email);

        List<Long> ids = notificationService.findPendingNotifications().stream()
                .map(Notification::getId)
                .collect(Collectors.toList());

        assertThat(ids, CoreMatchers.hasItem(id));
    }

    @Test
    public void success() throws Exception {
        Email email = newEmail();
        notificationService.add(email);

        Notification notification = notificationService.findPendingNotifications().get(0);
        notificationService.success(notification);
    }

    @Test
    public void failure() throws Exception {
        Email email = newEmail();
        notificationService.add(email);

        Notification notification = notificationService.findPendingNotifications().get(0);
        notificationService.success(notification);
    }

    private Email newEmail() {
        return new Email("to", "subj", new byte[] {33}, Collections.emptyList());
    }

}
