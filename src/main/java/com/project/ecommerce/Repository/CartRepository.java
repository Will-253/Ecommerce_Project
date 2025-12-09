package com.project.ecommerce.Repository;

import com.project.ecommerce.Model.CartItem;
import com.project.ecommerce.Model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface CartRepository extends JpaRepository<CartItem, Long> {

    Page<CartItem> findAllByUser_Username(String userUsername, Pageable pageable);

    Optional<CartItem> findByIdAndUserUsername(Long id, String userUsername);

    void deleteByUser_Username(String userUsername);

    long deleteByIdAndUserUsername(Long id, String userUsername);

    List<CartItem> findByUser(Users user);
}
