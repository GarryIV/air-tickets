package com.garryiv.air_tickets.core.services.notification;

import java.util.Map;

interface MessageBuilderService extends MessageBuilderFactory {
    byte[] buildContent(String template, Map<String, Object> contextMap);
    byte[] buildAttachment(String template, Map<String, Object> contextMap);
}
