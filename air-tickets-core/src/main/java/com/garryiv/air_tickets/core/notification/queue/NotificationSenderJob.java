package com.garryiv.air_tickets.core.notification.queue;

import com.garryiv.air_tickets.core.notification.email.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public static final Logger logger = LoggerFactory.getLogger(NotificationSenderJob.class);

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
        int pendingSize = pendingNotifications.size();
        if(pendingSize > 0) {
            logger.info("{} mails is pending", pendingSize);
        } else {
            logger.trace("no pending mail");
        }
        for (Notification notification : pendingNotifications) {
            try {
                trySend(notification.getPayload());
                notificationService.success(notification);
            } catch (Exception e) {
                logger.info("can't send email", e);
                notificationService.failure(notification);
            }
        }
    }

    private void trySend(Email email) throws MailSendException{
        mailSender.send(mimeMessage -> {
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email.getRecipient()));
            mimeMessage.setSubject(email.getSubject());
            mimeMessage.setContent(email.getSubject(), "text/html; charset=utf-8");
        });
    }
}
