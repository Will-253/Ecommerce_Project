package com.project.ecommerce.Controller;

import com.project.ecommerce.DTO.JwtAuthenticationResponse;
import com.project.ecommerce.DTO.RefreshTokenRequest;
import com.project.ecommerce.DTO.SignInRequest;
import com.project.ecommerce.DTO.SignUpRequest;
import com.project.ecommerce.Model.Users;
import com.project.ecommerce.Service.Interface.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/")
    public String welcome() {
        return "Welcome to the E-commerce API!";
    }

    @PostMapping("/signup")
    public ResponseEntity<Users> register(@RequestBody SignUpRequest signUpRequest){

        return ResponseEntity.ok(authenticationService.SignUp(signUpRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody SignInRequest signInRequest){
        return ResponseEntity.ok(authenticationService.SignIn(signInRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }

}
