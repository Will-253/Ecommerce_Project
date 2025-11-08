package com.project.ecommerce.Controller;

import com.project.ecommerce.DTO.JwtAuthenticationResponse;
import com.project.ecommerce.DTO.RefreshTokenRequest;
import com.project.ecommerce.DTO.SignInRequest;
import com.project.ecommerce.DTO.SignUpRequest;
import com.project.ecommerce.Model.EcommerceUser;
import com.project.ecommerce.Service.Interface.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<EcommerceUser> register(@RequestBody SignUpRequest signUpRequest){

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
