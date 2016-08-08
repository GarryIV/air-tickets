package com.garryiv.air_tickets.core.services.notification.template;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Interpolates html templates using Velocity
 */
public class HtmlTemplateInterpolator implements TemplateInterpolator {
    private TemplateEngine templateEngine ;

    public HtmlTemplateInterpolator() {
        this.templateEngine = new TemplateEngine();
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateEngine.setTemplateResolver(templateResolver);
    }

    @Override
    public byte[] interpolate(String template, Map<String, Object> contextMap) {
        Context context = new Context();
        context.setVariables(contextMap);
        return templateEngine.process(template, context).getBytes(StandardCharsets.UTF_8);
    }
}
