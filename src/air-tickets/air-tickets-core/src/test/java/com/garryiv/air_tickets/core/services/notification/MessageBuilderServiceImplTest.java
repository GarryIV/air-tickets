package com.garryiv.air_tickets.core.services.notification;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessageBuilderServiceImplTest {

    @Autowired
    private MessageBuilderService messageBuilderService;

    @Test
    public void newEmail() {
        Email email = messageBuilderService.newEmail("test")
                .withContext("var1", "val1")
                .withContext("var2", "val2")
                .build();

        assertNotNull(email);
    }

}