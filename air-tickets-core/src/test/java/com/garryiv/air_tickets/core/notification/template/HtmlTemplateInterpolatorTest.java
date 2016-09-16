package com.garryiv.air_tickets.core.notification.template;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertNotNull;

public class HtmlTemplateInterpolatorTest {

    private HtmlTemplateInterpolator interpolator = new HtmlTemplateInterpolator();

    @Test
    public void interpolate() throws Exception {
        HashMap<String, Object> contextMap = new HashMap<>();
        contextMap.put("name", "value");
        byte[] bytes = interpolator.interpolate("test.xhtml", contextMap);

        assertNotNull(bytes);
    }

}
