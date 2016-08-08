package com.garryiv.air_tickets.core.services.notification.queue;

import com.garryiv.air_tickets.core.services.notification.email.Email;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "NOTIFICATION_TBL")
@ToString(exclude = "payload")
public class Notification {
    @Id
    @Column(name = "notification_id")
    @GeneratedValue
    private Long id;

    private Email payload;

    private int failures = 0;

    private NotificationStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Email getPayload() {
        return payload;
    }

    public void setPayload(Email payload) {
        this.payload = payload;
    }

    public int getFailures() {
        return failures;
    }

    public void setFailures(int failures) {
        this.failures = failures;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }
}
