package com.project.ecommerce.DTO;

public class SignUpRequest {

    private String Username;
    private String Password;

    public SignUpRequest() {}

    public SignUpRequest(String username, String password) {
        Username = username;
        Password = password;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
