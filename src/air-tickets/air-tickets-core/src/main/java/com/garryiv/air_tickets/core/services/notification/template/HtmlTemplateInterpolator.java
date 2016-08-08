package com.garryiv.air_tickets.core.services.notification.template;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Interpolates html templates using Velocity
 */
public class HtmlTemplateInterpolator implements TemplateInterpolator {
    private VelocityEngine velocityEngine ;

    public HtmlTemplateInterpolator(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

    @Override
    public byte[] interpolate(String template, Map<String, Object> contextMap) {
        VelocityContext velocityContext = new VelocityContext(contextMap);
        try(
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                OutputStreamWriter writer = new OutputStreamWriter(baos)
        ) {
            velocityEngine.mergeTemplate(template, StandardCharsets.UTF_8.toString(),velocityContext, writer);
            writer.flush();
            return baos.toByteArray();
        } catch (IOException e) {
            throw new InterpolationException(template, e);
        }
    }
}
