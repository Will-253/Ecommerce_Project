package com.project.ecommerce.Service.Interface;

import com.project.ecommerce.DTO.SignUpRequest;
import com.project.ecommerce.Model.EcommerceUser;

public interface AuthenticationService {

    public EcommerceUser SignUp(SignUpRequest signUpRequest);
}
