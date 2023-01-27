package com.globallogic.library.controllers;

import com.globallogic.library.entities.User;
import com.globallogic.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for user creation and authentication
 */
@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/signup")
    public User createUser(@RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping(path = "/login")
    public ResponseEntity<String> login(@RequestParam(name = "username") String userName,
                                        @RequestParam(name = "password") String password) {
        boolean isUserAuthenticated = userService.authenticateUserCredentials(userName, password);
        if (isUserAuthenticated) {
            return ResponseEntity.status(200)
                    .body("USER LOGIN SUCCESS");
        }
        return ResponseEntity.status(401)
                .body("USER LOGIN FAILED");
    }
}
