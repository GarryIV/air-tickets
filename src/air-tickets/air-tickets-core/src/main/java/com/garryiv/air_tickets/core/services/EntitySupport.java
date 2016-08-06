package com.garryiv.air_tickets.core.services;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class EntitySupport {
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
