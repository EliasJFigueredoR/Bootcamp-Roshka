package ejercicio.springboot.hibernate.services;

import ejercicio.springboot.hibernate.dto.request.LoginRequest;
import ejercicio.springboot.hibernate.dto.request.RegisterRequest;
import ejercicio.springboot.hibernate.dto.response.AuthResponse;
import ejercicio.springboot.hibernate.models.User;
import ejercicio.springboot.hibernate.repositorys.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        // Esto valida si el usuario y contraseña son correctos
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        // Si llega aquí es porque la autenticación fue exitosa
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);

        return new AuthResponse(token);
    }

    public AuthResponse register(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        User usuarioGuardado = userRepository.save(user);

        return new AuthResponse(jwtService.getToken(user));

    }
}
