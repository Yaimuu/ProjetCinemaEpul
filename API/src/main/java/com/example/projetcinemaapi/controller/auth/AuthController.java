package com.example.projetcinemaapi.controller.auth;

import com.example.projetcinemaapi.domains.auth.User;
import com.example.projetcinemaapi.security.jwt.JwtUtil;
import com.example.projetcinemaapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtTokenUtil;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticate(loginRequest.getUsername(), loginRequest.getPassword());
            String accessToken = jwtTokenUtil.generateAccessToken(authentication);
            String refreshToken = jwtTokenUtil.generateRefreshToken(authentication);
            return ResponseEntity.ok(new JwtResponse(accessToken, refreshToken));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshToken(@RequestHeader("Authorization") String authorization) {
        String refreshToken = authorization.substring("Bearer ".length());
        String username = jwtTokenUtil.getUserName(refreshToken);
        User user = userService.getUserByUsername(username);
        String accessToken = jwtTokenUtil.generateAccessToken(user.getId(), "ROLE_" + user.getRole());
        return ResponseEntity.ok(new JwtResponse(accessToken, refreshToken));
    }

    private Authentication authenticate(String username, String password) {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

}


