package com.garryiv.air_tickets.core.services.notification;

public class EmailAttachment {
    private final String name;
    private final byte[] content;

    public EmailAttachment(String name, byte[] content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public byte[] getContent() {
        return content;
    }
}
