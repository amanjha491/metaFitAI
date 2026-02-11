package com.metaFitAi.userService.controller;

import com.metaFitAi.userService.dto.RegisterRequest;
import com.metaFitAi.userService.dto.UserResponse;
import com.metaFitAi.userService.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/metaFitAi/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserProfile(@PathVariable String userId) {
        System.out.println("into userId endpoint");
        return ResponseEntity.ok(userService.getUserProfile(userId));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request) {
        System.out.println("into register endpoint");
        return ResponseEntity.ok(userService.register(request));
    }

    @GetMapping("/{userId}/validate")
    public ResponseEntity<Boolean> validateUser(@PathVariable String userId) {
        System.out.println("into validation endpoint");
        return ResponseEntity.ok(userService.existByUserId(userId));
    }
}
