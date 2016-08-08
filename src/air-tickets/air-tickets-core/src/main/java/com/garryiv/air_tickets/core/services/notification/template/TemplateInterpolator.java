package com.garryiv.air_tickets.core.services.notification.template;

import java.util.Map;

/**
 * Interpolates message template
 */
public interface TemplateInterpolator {

    /**
     * Interpolates template to byte array
     * @param template template, interpolator should use spring to load templates
     * @param contextMap context variables
     * @return report as byte array
     */
    byte[] interpolate(String template, Map<String, Object> contextMap);
}
