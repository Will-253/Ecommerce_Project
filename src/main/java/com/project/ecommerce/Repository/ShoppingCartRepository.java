package com.project.ecommerce.Repository;

import com.project.ecommerce.Model.CartItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ShoppingCartRepository extends JpaRepository<CartItem, Long> {

    Page<CartItem> findAllByUser_Username(String userUsername, Pageable pageable);
}
