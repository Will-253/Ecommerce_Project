package com.project.ecommerce.Service.Impl;

import com.project.ecommerce.DTO.JwtAuthenticationResponse;
import com.project.ecommerce.DTO.RefreshTokenRequest;
import com.project.ecommerce.DTO.SignInRequest;
import com.project.ecommerce.DTO.SignUpRequest;
import com.project.ecommerce.Model.EcommerceUser;
import com.project.ecommerce.Model.Role;
import com.project.ecommerce.Repository.UserRepository;
import com.project.ecommerce.Service.Interface.AuthenticationService;
import com.project.ecommerce.Service.Interface.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public EcommerceUser SignUp(SignUpRequest signUpRequest){

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new IllegalArgumentException("Username is already taken!");
        }

        EcommerceUser user = new EcommerceUser();

        user.setUsername(signUpRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRole(Role.USER);

        return userRepository.save(user);
    }

    public JwtAuthenticationResponse SignIn(SignInRequest signInRequest){

       Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInRequest.getUsername(),
                signInRequest.getPassword())
       );

//        var user = userRepository.findByUsername(signInRequest.getUsername())
//                .orElseThrow(()-> new IllegalArgumentException("Invalid username or password"));

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        var jwt = jwtService.generateToken(userDetails);

        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), userDetails);

        JwtAuthenticationResponse response = new JwtAuthenticationResponse();
        response.setToken(jwt);
        response.setRefreshToken(refreshToken);

        return response;
    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest){

        String username = jwtService.extractUserName(refreshTokenRequest.getToken());

       EcommerceUser user = userRepository.findByUsername(username).orElseThrow();

        if(jwtService.validateToken(refreshTokenRequest.getToken(), (UserDetails) user)){

            var jwt= jwtService.generateToken((UserDetails) user);

            JwtAuthenticationResponse response = new JwtAuthenticationResponse();
            response.setToken(jwt);
            response.setRefreshToken(refreshTokenRequest.getToken());

            return response;

        }

        return null;
    }

}
