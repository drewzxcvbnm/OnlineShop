package com.online.shop.application.services.user;

import com.online.shop.application.dto.UserDto;
import com.online.shop.application.entities.Authority;
import com.online.shop.application.entities.User;
import com.online.shop.application.entities.UserInfo;
import com.online.shop.application.exceptions.UserNotFoundException;
import com.online.shop.application.mappers.UserMapper;
import com.online.shop.application.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public String getCurrentUsername() {
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
    }

    public Optional<User> getCurrentUser() {
        String username = getCurrentUsername();
        return userRepo.findByUsername(username);
    }

    public User getObligatoryCurrentUser() {
        String username = getCurrentUsername();
        return userRepo.findByUsername(username)
                .orElseThrow(UserNotFoundException.supplier("Cannot get current user"));
    }

    public Optional<UserInfo> getCurrentUserInfo() {
        return getCurrentUser()
                .map(User::getUserInfo);
    }

    public boolean isAdmin() {
        return getCurrentUser()
                .map(user -> Authority.ADMIN.equals(user.getAuthority()))
                .orElse(false);
    }

    public boolean userIdIsCurrent(long userId) {
        return getCurrentUser()
                .map(u -> u.getId() == userId)
                .orElse(false);
    }

    @Transactional
    public void saveNewUser(UserDto userDto) {
        User user = userMapper.toUser(userDto);
        user.setAuthority(Authority.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

}
