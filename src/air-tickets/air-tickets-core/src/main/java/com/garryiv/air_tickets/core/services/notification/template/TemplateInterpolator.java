package com.garryiv.air_tickets.core.services.notification.template;

import java.util.Map;

public interface TemplateInterpolator {
    byte[] interpolate(String template, Map<String, Object> contextMap);
}
