package fr.efrei.tp.controller;

import fr.efrei.tp.dto.LoginDto;
import fr.efrei.tp.dto.RegisterDto;
import fr.efrei.tp.dto.LoginResponse;
import fr.efrei.tp.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        authService.signup(registerDto);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDto loginDto) {
        String token = authService.authenticate(loginDto);
        return ResponseEntity.ok(new LoginResponse(token));
    }

}
