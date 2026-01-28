package ejercicio.springboot.hibernate.controller;

import ejercicio.springboot.hibernate.dto.request.LoginRequest;
import ejercicio.springboot.hibernate.dto.request.RegisterRequest;
import ejercicio.springboot.hibernate.dto.response.AuthResponse;
import ejercicio.springboot.hibernate.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("login")
    public ResponseEntity<AuthResponse> login (@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authService.register(registerRequest));
    }


}

