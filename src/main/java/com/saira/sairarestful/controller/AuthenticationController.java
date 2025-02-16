package com.saira.sairarestful.controller;

import com.saira.sairarestful.dtos.LoginResponseDto;
import com.saira.sairarestful.dtos.LoginUserDto;
import com.saira.sairarestful.dtos.RegisterUserDto;
import com.saira.sairarestful.entity.User;
import com.saira.sairarestful.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
    @PostMapping("/signup")
    public ResponseEntity<User> register(
            @Valid @RequestBody RegisterUserDto registerUserDto
    ) {
        User registeredUser = authenticationService.signup(registerUserDto);
        return ResponseEntity.ok(registeredUser);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> authenticate(
            @RequestBody LoginUserDto loginUserDto
    ) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        // Placeholder for JWT token generation logic
        String jwtToken = "dummyToken"; // Replace with actual JWT generation logic
        LoginResponseDto loginResponse = new LoginResponseDto()
                .setToken(jwtToken)
                .setExpiresIn(
                        ChronoUnit.MINUTES.between(
                                Instant.now(),
                                Instant.now().plus(30, ChronoUnit.MINUTES)
                        )
                );

        return ResponseEntity.ok(loginResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex
    ) {
        Map<String, String> errors = new HashMap<>();
        ex
                .getBindingResult()
                .getAllErrors()
                .forEach(error -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
