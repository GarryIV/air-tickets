package com.garryiv.air_tickets.core.services.user;

import com.garryiv.air_tickets.api.user.UserInfo;
import com.garryiv.air_tickets.api.user.UserService;
import com.garryiv.air_tickets.core.services.CoreServiceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@CoreServiceTest
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

    @Test
    public void find() {
        UserInfo newUser = userService.findOrCreate("email@gmail.com");

        UserInfo found = userService.find(newUser.getId());
        assertNotNull(found);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findNull() {
        userService.find(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findNonExisting() {
        userService.find(Long.MAX_VALUE);
    }

}