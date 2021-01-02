package com.mbouhda.reddit.service;

import com.mbouhda.reddit.exception.JwtException;
import com.mbouhda.reddit.model.RefreshToken;
import com.mbouhda.reddit.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class RefreshTokenService {

    private final RefreshTokenRepository repository;

    public RefreshToken generate() {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setCreatedDate(Instant.now());
        refreshToken.setToken(UUID.randomUUID().toString());
        return repository.save(refreshToken);
    }

    public void validate(String token) {
        repository.findByToken(token)
                .orElseThrow(() -> new JwtException("Refresh token is invalid."));
    }

    public void delete(String token) {
        repository.deleteByToken(token);
    }
}
