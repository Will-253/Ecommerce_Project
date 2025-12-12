package com.project.ecommerce.Repository;

import com.project.ecommerce.Model.Orders;
import com.project.ecommerce.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Orders, Long> {

    List<Orders> findByUser(Users user);

    Optional<Orders> findByIdAndUser(Long id, Users user);
}
