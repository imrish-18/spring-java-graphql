package com.globallogic.library.services.impl;

import com.globallogic.library.entities.User;
import com.globallogic.library.repositories.UserRepository;
import com.globallogic.library.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        log.info("[USERSVC] saving user details " + user.getEmailId());
        return this.userRepository.save(user);
    }

    @Override
    public boolean authenticateUserCredentials(String userName, String password) {
        List<User> userList = this.userRepository.findByEmailIdAndPassword(userName, password);
        log.info("[USERSVC] checking user credentials ");
        return !CollectionUtils.isEmpty(userList);
    }
}
