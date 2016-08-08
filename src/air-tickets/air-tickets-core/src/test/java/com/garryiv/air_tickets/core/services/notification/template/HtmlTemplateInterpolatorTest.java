package com.garryiv.air_tickets.core.services.notification.template;

import com.garryiv.air_tickets.core.services.CoreServiceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@CoreServiceTest
public class HtmlTemplateInterpolatorTest {

    @Autowired
    private HtmlTemplateInterpolator interpolator;

    @Test
    public void interpolate() throws Exception {
        HashMap<String, Object> contextMap = new HashMap<>();
        contextMap.put("name", "value");
        byte[] bytes = interpolator.interpolate("test.vm", contextMap);

        assertNotNull(bytes);
    }

}