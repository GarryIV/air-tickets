package com.garryiv.air_tickets.core.services.notification;

import java.util.List;

public class Email {
    private final String recipient;
    private final byte[] body;
    private final List<EmailAttachment> attachments;

    public Email(String recipient, byte[] body, List<EmailAttachment> attachments) {
        this.recipient = recipient;
        this.body = body;
        this.attachments = attachments;
    }

    public String getRecipient() {
        return recipient;
    }

    public byte[] getBody() {
        return body;
    }

    public List<EmailAttachment> getAttachments() {
        return attachments;
    }
}
