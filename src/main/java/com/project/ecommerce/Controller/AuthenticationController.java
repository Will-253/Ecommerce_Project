package com.project.ecommerce.Controller;

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


}
