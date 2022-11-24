package com.example.projetcinemaapi.controller.user;

import com.example.projetcinemaapi.service.AuthService;
import com.example.projetcinemaapi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final AuthService authService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUsers(@PathVariable String id) {
        if (authService.isAuthenticated(id)) {
            return ResponseEntity.ok(UserResponse.fromDomain(userService.getUserByUsername(id)));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
