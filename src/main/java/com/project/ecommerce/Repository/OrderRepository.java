package com.project.ecommerce.Repository;

import com.project.ecommerce.Model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {

}
