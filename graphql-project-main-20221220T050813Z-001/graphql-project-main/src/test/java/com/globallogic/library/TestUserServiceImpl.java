package com.globallogic.library;

import com.globallogic.library.entities.User;
import com.globallogic.library.model.Gender;
import com.globallogic.library.repositories.UserRepository;
import com.globallogic.library.services.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestUserServiceImpl {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;
    @Test
    public void testCreateUser() {
        User user = User.builder()
                .firstName("Deepthy")
                .lastName("George")
                .emailId("deepthy.george@globallogic.com")
                .password("password")
                .gender(Gender.F)
                .contact("9900919937")
                .build();
        Mockito.when(userRepository.save(Mockito.any()))
                .thenAnswer(invocationOnMock -> {
                    User userMock = (User) invocationOnMock.getArguments()[0];
                    userMock.setId(1);
                    return userMock;
                });
        Assert.assertEquals(1, userService.create(user).getId());
    }

}
