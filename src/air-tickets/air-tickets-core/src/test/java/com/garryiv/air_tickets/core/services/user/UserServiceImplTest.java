package com.garryiv.air_tickets.core.services.user;

import com.garryiv.air_tickets.api.user.UserInfo;
import com.garryiv.air_tickets.api.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void findOrCreate() {
        UserInfo newUser = userService.findOrCreate("email@gmail.com");

        assertNotNull(newUser);
        assertNotNull(newUser.getEmail());
        assertNotNull(newUser.getId());

        // service should be idempotent
        UserInfo existingUser = userService.findOrCreate("email@gmail.com");
        assertNotNull(existingUser);

        assertEquals(newUser.getId(), existingUser.getId());
        assertEquals(newUser.getEmail(), existingUser.getEmail());
    }
}