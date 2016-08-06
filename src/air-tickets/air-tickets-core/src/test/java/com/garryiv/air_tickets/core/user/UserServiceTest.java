package com.garryiv.air_tickets.core.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void findOrCreate() {
        User newUser = userService.findOrCreate("email@gmail.com");

        assertNotNull(newUser);
        assertNotNull(newUser.getEmail());
        assertNotNull(newUser.getId());

        // service should be idempotent
        User existingUser = userService.findOrCreate("email@gmail.com");
        assertNotNull(existingUser);

        assertEquals(newUser.getId(), existingUser.getId());
        assertEquals(newUser.getEmail(), existingUser.getEmail());
    }
}