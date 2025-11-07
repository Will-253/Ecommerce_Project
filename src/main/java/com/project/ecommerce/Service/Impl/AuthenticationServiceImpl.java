package com.project.ecommerce.Service.Impl;

import com.project.ecommerce.DTO.SignUpRequest;
import com.project.ecommerce.Model.EcommerceUser;
import com.project.ecommerce.Model.Role;
import com.project.ecommerce.Repository.UserRepository;
import com.project.ecommerce.Service.Interface.AuthenticationService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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

}
