package com.online.shop.application.services.user;

import com.online.shop.application.entities.User;
import com.online.shop.application.repositories.UserRepo;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class UserImageService {

    private final UserRepo userRepo;

    private final byte[] defaultImage;

    @SneakyThrows
    public UserImageService(UserRepo userRepo) {
        this.userRepo = userRepo;
        this.defaultImage = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("static/images/profile_2.png")
                .readAllBytes();
    }

    public byte[] getUserImage(long userId) {
        return userRepo.findById(userId)
                .map(User::getProfilePicture)
                .orElse(defaultImage);
    }

}
