package me.jaebong.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.jaebong.springbootdeveloper.dto.CreateAccessTokenRequest;
import me.jaebong.springbootdeveloper.dto.CreateAccessTokenResponse;
import me.jaebong.springbootdeveloper.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TokenApiController {
    private final TokenService tokenService;

    // /api/token POST요청이 오면 토큰 서비스에서 리프레시 토큰을 기반으로 새로운 엑세스 토큰을 만들어줌
    @PostMapping("/api/token")
    public ResponseEntity<CreateAccessTokenResponse> createNewAccessToken
            (@RequestBody CreateAccessTokenRequest request){
        String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateAccessTokenResponse(newAccessToken));
    }
}
