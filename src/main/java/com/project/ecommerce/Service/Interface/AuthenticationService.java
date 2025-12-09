package com.project.ecommerce.Service.Interface;

import com.project.ecommerce.DTO.JwtAuthenticationResponse;
import com.project.ecommerce.DTO.RefreshTokenRequest;
import com.project.ecommerce.DTO.SignInRequest;
import com.project.ecommerce.DTO.SignUpRequest;
import com.project.ecommerce.Model.Users;

public interface AuthenticationService {

    Users SignUp(SignUpRequest signUpRequest);

    JwtAuthenticationResponse SignIn(SignInRequest signInRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
