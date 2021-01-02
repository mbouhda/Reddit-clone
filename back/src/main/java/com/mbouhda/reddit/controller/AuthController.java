package com.mbouhda.reddit.controller;

import com.mbouhda.reddit.dto.AuthResponse;
import com.mbouhda.reddit.dto.LoginRequest;
import com.mbouhda.reddit.dto.RefreshTokenDTO;
import com.mbouhda.reddit.dto.RegisterRequest;
import com.mbouhda.reddit.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody RegisterRequest dto) {
        authService.signup(dto);
        return new ResponseEntity<>("Registration successful.", HttpStatus.OK);
    }

    @PostMapping("/login")
    public AuthResponse logIn(@RequestBody LoginRequest dto) {
        return authService.login(dto);
    }

    @PostMapping("/refresh-token")
    public AuthResponse refresh(@RequestBody RefreshTokenDTO dto) {
        return authService.refreshToken(dto);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody RefreshTokenDTO dto) {
        authService.logout(dto.getRefreshToken());
        return ResponseEntity.status(HttpStatus.OK).body("Refresh token deleted.");
    }
}
