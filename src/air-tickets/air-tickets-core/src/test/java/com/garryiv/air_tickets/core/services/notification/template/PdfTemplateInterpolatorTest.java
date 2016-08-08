package com.garryiv.air_tickets.core.services.notification.template;

import org.junit.Test;
import org.springframework.core.io.DefaultResourceLoader;

import java.util.HashMap;

import static org.junit.Assert.assertNotNull;

public class PdfTemplateInterpolatorTest {

    @Test
    public void test() {
        PdfTemplateInterpolator interpolator = new PdfTemplateInterpolator(new DefaultResourceLoader());

        HashMap<String, Object> contextMap = new HashMap<>();
        contextMap.put("name", "value");
        byte[] bytes = interpolator.interpolate("classpath:/ODTHelloWordWithVelocity.odt", contextMap);

        assertNotNull(bytes);
    }
}