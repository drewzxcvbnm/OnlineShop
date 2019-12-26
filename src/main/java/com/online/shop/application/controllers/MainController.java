package com.online.shop.application.controllers;

import com.online.shop.application.entities.TestEntity;
import com.online.shop.application.repositories.TestRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final TestRepo repo;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        List<TestEntity> all = repo.findAll();
        System.out.println(all);
        return ResponseEntity.ok("done");
    }

}
