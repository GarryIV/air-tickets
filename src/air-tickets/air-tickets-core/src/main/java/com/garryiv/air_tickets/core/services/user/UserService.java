package com.garryiv.air_tickets.core.services.user;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * This service is responsible for searching and managing public users.
 */
@Transactional
@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Looks for a user in the repository. Returns existing or new user.
     * @param email email to search user
     * @return user
     */
    public User findOrCreate(String email) {
        if (StringUtils.isEmpty(email)) {
            throw new IllegalArgumentException("email is required");
        }

        User user = userRepository.findByEmail(email);
        if (user == null) {
            user = new User();
            user.setEmail(email);
            userRepository.save(user);
        }
        return user;
    }
}
