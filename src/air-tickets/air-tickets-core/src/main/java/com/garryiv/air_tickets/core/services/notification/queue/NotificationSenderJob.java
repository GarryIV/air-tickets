package com.garryiv.air_tickets.core.services.notification.queue;

import com.garryiv.air_tickets.core.services.notification.email.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import java.util.List;

@Service
public class NotificationSenderJob {

    private final JavaMailSender mailSender;

    private final NotificationService notificationService;

    @Autowired
    public NotificationSenderJob(JavaMailSender mailSender, NotificationService notificationService) {
        this.mailSender = mailSender;
        this.notificationService = notificationService;
    }


    @Scheduled(fixedDelay = 10000)
    public void sendNotifications() {
        List<Notification> pendingNotifications = notificationService.findPendingNotifications();
        for (Notification notification : pendingNotifications) {
            try {
                trySend(notification.getPayload());
                notificationService.success(notification);
            } catch (MailSendException e) {
                notificationService.failure(notification);
            }
        }
    }

    private void trySend(Email email) throws MailSendException{
        mailSender.send(mimeMessage -> {
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email.getRecipient()));
            mimeMessage.setSubject(email.getSubject());
        });
    }
}
