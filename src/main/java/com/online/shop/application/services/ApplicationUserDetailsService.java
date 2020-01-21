package com.online.shop.application.services;

import com.online.shop.application.configuration.ApplicationUserPrincipal;
import com.online.shop.application.entities.User;
import com.online.shop.application.exceptions.UserNotFoundException;
import com.online.shop.application.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(UserNotFoundException.supplier("Cannot find user by username:[%s]", username));
        return new ApplicationUserPrincipal(user);
    }
}
