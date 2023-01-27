package com.globallogic.library.services;

import com.globallogic.library.entities.User;

public interface UserService {
    /**
     * Creates a new user
     * @param user
     * @return
     */
    User create(User user);

    /**
     * Authenticates user using username/email and password
     * @param userName
     * @param password
     * @return
     */
    boolean authenticateUserCredentials(String userName, String password);
}
