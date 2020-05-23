package com.online.shop.application.services.user;

import com.online.shop.application.dto.UserDto;
import com.online.shop.application.entities.Authority;
import com.online.shop.application.entities.User;
import com.online.shop.application.entities.UserInfo;
import com.online.shop.application.mappers.UserMapper;
import com.online.shop.application.repositories.UserRepo;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepo userRepo;
    @Mock
    private UserMapper userMapper;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserService userService;

    @Mock
    SecurityContext securityContext;
    @Mock
    Authentication authentication;

    private static SecurityContext context;

    @BeforeClass
    public static void saveContext() {
        context = SecurityContextHolder.getContext();
    }

    @AfterClass
    public static void restoreContext() {
        SecurityContextHolder.setContext(context);
    }

    @Before
    public void setup() {
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("NAME");
    }

    @Test
    public void getCurrentUsername() {
        assertThat(userService.getCurrentUsername()).isEqualTo("NAME");
    }

    @Test
    public void getCurrentUser() {
        User user = getUser();
        when(userRepo.findByUsername("NAME")).thenReturn(Optional.of(user));
        assertThat(userService.getCurrentUser().get()).isEqualTo(user);
    }

    @Test
    public void getCurrentUserInfo() {
        User user = getUser();
        when(userRepo.findByUsername("NAME")).thenReturn(Optional.of(user));
        assertThat(userService.getCurrentUserInfo().get()).isEqualTo(user.getUserInfo());
    }

    @Test
    public void isAdmin() {
        when(userRepo.findByUsername("NAME")).thenReturn(Optional.of(getUser(Authority.ADMIN)));
        assertThat(userService.isAdmin()).isTrue();
        when(userRepo.findByUsername("NAME")).thenReturn(Optional.of(getUser(Authority.USER)));
        assertThat(userService.isAdmin()).isFalse();
    }

    @Test
    public void saveNewUser() {
        User user = getUser();
        UserDto userDto = new UserDto();
        when(userMapper.toUser(userDto)).thenReturn(user);
        when(passwordEncoder.encode("pass")).thenReturn("ssap");
        userService.saveNewUser(userDto);
        assertThat(user.getAuthority()).isEqualTo(Authority.USER);
        assertThat(user.getPassword()).isEqualTo("ssap");
        verify(userRepo).save(user);
    }

    private User getUser() {
        User user = new User();
        user.setUserInfo(new UserInfo());
        user.setPassword("pass");
        return user;
    }

    private User getUser(Authority authority) {
        User user = new User();
        user.setAuthority(authority);
        user.setUserInfo(new UserInfo());
        return user;
    }

}