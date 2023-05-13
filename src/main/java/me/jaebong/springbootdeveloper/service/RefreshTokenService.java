package me.jaebong.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.jaebong.springbootdeveloper.domain.RefreshToken;
import me.jaebong.springbootdeveloper.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    //전달받은 리프레시 토큰 객체를 검색해서 전달하는 메서드
    public RefreshToken findByRefreshToken(String refreshToken){
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected token"));
    }
}
