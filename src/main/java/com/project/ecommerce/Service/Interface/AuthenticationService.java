package com.project.ecommerce.Service.Interface;

import com.project.ecommerce.DTO.JwtAuthenticationResponse;
import com.project.ecommerce.DTO.RefreshTokenRequest;
import com.project.ecommerce.DTO.SignInRequest;
import com.project.ecommerce.DTO.SignUpRequest;
import com.project.ecommerce.Model.EcommerceUser;

public interface AuthenticationService {

    public EcommerceUser SignUp(SignUpRequest signUpRequest);

    public JwtAuthenticationResponse SignIn(SignInRequest signInRequest);

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
