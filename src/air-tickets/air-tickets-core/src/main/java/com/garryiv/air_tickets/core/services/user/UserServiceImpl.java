package com.garryiv.air_tickets.core.services.user;

import com.garryiv.air_tickets.api.user.UserInfo;
import com.garryiv.air_tickets.api.user.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
    public UserInfo findOrCreate(@PathVariable String email) {
        if (StringUtils.isEmpty(email)) {
            throw new IllegalArgumentException("email is required");
        }

        User user = userRepository.findByEmail(email);
        if (user == null) {
            user = new User();
            user.setEmail(email);
            userRepository.save(user);
        }
        return toUserInfo(user);
    }

    private UserInfo toUserInfo(User user) {
        UserInfo info = new UserInfo();
        BeanUtils.copyProperties(user, info);
        return info;
    }
}
