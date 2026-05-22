package redis.com.RankRedis.controller;

import org.springframework.web.bind.annotation.*;
import redis.com.RankRedis.model.dto.LoginDTO;
import redis.com.RankRedis.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO dto) {
        return authService.loginUser(dto);
    }

    @PostMapping("/logout")
    public String logout(@RequestHeader("Authorization") String authorization) {
        return authService.logoutUser(authorization);
    }

}