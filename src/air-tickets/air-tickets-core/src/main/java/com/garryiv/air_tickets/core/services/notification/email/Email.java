package com.garryiv.air_tickets.core.services.notification.email;

import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@ToString(exclude = "body")
public class Email implements Serializable {
    public static final int serialVersionUID = 1;

    private final String recipient;
    private final String subject;
    private final byte[] body;
    private final List<EmailAttachment> attachments;

    public Email(String recipient, String subject, byte[] body, List<EmailAttachment> attachments) {
        this.recipient = recipient;
        this.subject = subject;
        this.body = body;
        this.attachments = attachments;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getSubject() {
        return subject;
    }

    public byte[] getBody() {
        return body;
    }

    public List<EmailAttachment> getAttachments() {
        return attachments;
    }
}
