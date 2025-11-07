package com.project.ecommerce;

import com.project.ecommerce.Model.EcommerceUser;
import com.project.ecommerce.Model.Role;
import com.project.ecommerce.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        EcommerceUser Admin= userRepository.findByRole(Role.ADMIN);

        if(Admin==null) {
            EcommerceUser user = new EcommerceUser();
            user.setUsername("admin");
            user.setPassword(new BCryptPasswordEncoder(12).encode("admin"));
            user.setRole(Role.ADMIN);

            userRepository.save(user);
        }

    }
}
