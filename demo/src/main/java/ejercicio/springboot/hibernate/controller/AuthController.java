package ejercicio.springboot.hibernate.controller;

import ejercicio.springboot.hibernate.dto.request.LoginRequest;
import ejercicio.springboot.hibernate.dto.request.RegisterRequest;
import ejercicio.springboot.hibernate.dto.response.AuthResponse;
import ejercicio.springboot.hibernate.dto.response.UserResponse;
import ejercicio.springboot.hibernate.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login (@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse>me(@AuthenticationPrincipal UserDetails userDetails)
    {
        return ResponseEntity.ok(authService.me(userDetails.getUsername()));
    }


}

