package com.mbouhda.reddit.service;

import com.mbouhda.reddit.config.ConfigProperties;
import com.mbouhda.reddit.config.security.JwtProvider;
import com.mbouhda.reddit.dto.AuthResponse;
import com.mbouhda.reddit.dto.LoginRequest;
import com.mbouhda.reddit.dto.RefreshTokenDTO;
import com.mbouhda.reddit.dto.RegisterRequest;
import com.mbouhda.reddit.model.User;
import com.mbouhda.reddit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final ConfigProperties properties;
    private final RefreshTokenService refreshTokenService;

    @Transactional
    public void signup(RegisterRequest dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setCreated(Instant.now());
        userRepository.save(user);
    }

    public AuthResponse login(LoginRequest dto) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                dto.getUsername(), dto.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        String token = jwtProvider.createToken(dto.getUsername());

        return AuthResponse.builder()
                .refreshToken(refreshTokenService.generate().getToken())
                .token(token)
                .username(dto.getUsername())
                .expiresAt(Instant.now().plusMillis(properties.getExpire()))
                .build();
    }

    public AuthResponse refreshToken(RefreshTokenDTO dto) {
        refreshTokenService.validate(dto.getRefreshToken());
        String token = jwtProvider.createToken(dto.getUsername());
        return AuthResponse.builder()
                .expiresAt(Instant.now().plusMillis(properties.getExpire()))
                .username(dto.getUsername())
                .token(token)
                .refreshToken(dto.getRefreshToken())
                .build();
    }

    public void logout(String token) {
        refreshTokenService.delete(token);
    }
}
