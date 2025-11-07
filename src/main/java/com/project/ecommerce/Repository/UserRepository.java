package com.project.ecommerce.Repository;

import com.project.ecommerce.Model.EcommerceUser;
import com.project.ecommerce.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<EcommerceUser, Long> {

  Optional<EcommerceUser> findByUsername(String username);

    boolean existsByUsername(String username);

  EcommerceUser findByRole(Role role);
}
