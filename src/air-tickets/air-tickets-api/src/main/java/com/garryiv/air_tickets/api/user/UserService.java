package com.garryiv.air_tickets.api.user;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * This service is responsible for searching and managing public users.
 */
public interface UserService {

    /**
     * Looks for a user in the repository. Returns existing or throw exception.
     * @param accessKey email to search user
     * @return user
     */
    @RequestMapping(method = RequestMethod.GET, value = "/api/user/accesskey/{accessKey}")
    UserInfo findByAccessKey(@PathVariable("accessKey") String accessKey);

    /**
     * Looks for a user in the repository. Returns existing or new user.
     * @param email email to search user
     * @return user
     */
    @RequestMapping(method = RequestMethod.GET, value = "/api/user/email")
    UserInfo findOrCreate(@RequestParam("email") String email);

    /**
     * Get user by id or throw an exception
     * @param userId user id to get
     * @return user
     */
    @RequestMapping(method = RequestMethod.GET, value = "/api/user/{userId}")
    UserInfo find(@PathVariable("userId") Long userId);
}
