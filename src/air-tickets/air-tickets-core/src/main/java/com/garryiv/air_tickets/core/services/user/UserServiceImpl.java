package com.garryiv.air_tickets.core.services.user;

import com.garryiv.air_tickets.api.user.UserInfo;
import com.garryiv.air_tickets.api.user.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Transactional
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserInfo findByAccessKey(@RequestParam String accessKey) {
        User user = userRepository.findByAccessKey(accessKey);
        if(user == null) {
            return null;
        }
        return toUserInfo(user);
    }

    @Override
    public UserInfo findOrCreate(@RequestParam String email) {
        Assert.notNull(email, "email");

        User user = userRepository.findByEmail(email);
        if (user == null) {
            user = new User();
            user.setEmail(email);
            user.setAccessKey(UUID.randomUUID().toString());
            userRepository.save(user);
        }
        return toUserInfo(user);
    }

    @Override
    public UserInfo find(@PathVariable Long userId) {
        Assert.notNull(userId, "userId");

        return toUserInfo(userRepository.findOne(userId));
    }

    private UserInfo toUserInfo(User user) {
        Assert.notNull(user, "user is not found");

        UserInfo info = new UserInfo();
        BeanUtils.copyProperties(user, info);
        return info;
    }
}
