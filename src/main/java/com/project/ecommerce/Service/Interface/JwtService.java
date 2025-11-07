package com.project.ecommerce.Service.Interface;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.function.Function;

public interface JwtService {

    public String generateToken(UserDetails userDetails);

    public String extractUserName(String token);

    public  <T> T extractClaim(String token, Function<Claims, T> claimResolver);

    public Claims extractAllClaims(String token);

    public boolean validateToken(String token, UserDetails userDetails);

    public boolean isTokenExpired(String token);

    public Date extractExpiration(String token);

}
