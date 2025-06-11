package com.fast_pos.fast_pos.adapter.in.controllers;

import com.fast_pos.fast_pos.adapter.in.dto.request.LoginRequest;
import com.fast_pos.fast_pos.adapter.in.dto.response.JwtResponse;
import com.fast_pos.fast_pos.application.service.UserDetailService;
import com.fast_pos.fast_pos.application.service.UserService;
import com.fast_pos.fast_pos.domain.model.User;
import com.fast_pos.fast_pos.infrastructure.config.security.JwtTokenProvider;
import com.fast_pos.fast_pos.infrastructure.config.security.UserPrincipal;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@CrossOrigin(origins = "http://localhost:3000")
@RestController @RequestMapping("/api/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        logger.info("Authenticating user {}", loginRequest.getEmail());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Cast al UserPrincipal para acceder al schema
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String tenantSchema = userPrincipal.getSchemaName();

        // Generar token con el schema incluido
        String jwt = tokenProvider.generateToken(authentication, tenantSchema);

        return ResponseEntity.ok(new JwtResponse(jwt));
    }
}
