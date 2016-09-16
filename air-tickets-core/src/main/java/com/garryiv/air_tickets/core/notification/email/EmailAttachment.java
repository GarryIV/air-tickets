package com.garryiv.air_tickets.core.notification.email;

import lombok.ToString;

import java.io.Serializable;

@ToString(exclude = "content")
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
