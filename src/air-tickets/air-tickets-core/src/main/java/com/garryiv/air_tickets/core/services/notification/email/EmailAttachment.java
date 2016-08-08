package com.garryiv.air_tickets.core.services.notification.email;

import java.io.Serializable;

public class EmailAttachment implements Serializable {
    public static final int serialVersionUID = 1;

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
