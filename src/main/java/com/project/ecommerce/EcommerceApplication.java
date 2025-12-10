package com.project.ecommerce;

import com.project.ecommerce.Model.Users;
import com.project.ecommerce.Model.Enums.Role;
import com.project.ecommerce.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner {

    private final UserRepository userRepository;

    public EcommerceApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Users Admin= userRepository.findByRole(Role.ADMIN);

        if(Admin==null) {
            Users user = new Users();
            user.setUsername("admin");
            user.setPassword(new BCryptPasswordEncoder(12).encode("admin"));
            user.setRole(Role.ADMIN);

            userRepository.save(user);
        }

    }
}
