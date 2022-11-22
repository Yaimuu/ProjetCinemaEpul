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
public class UtilisateurController {
    private final UserService userService;
    private final AuthService authService;

    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurResponse> getUsers(@PathVariable String id,
                                                @RequestHeader("Authorization") String authorization) {
        if (authService.isAdminOrAuthenticatedCandidat(id, authorization)) {
            return ResponseEntity.ok(UtilisateurResponse.fromDomain(userService.getUserByUsername(id)));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
