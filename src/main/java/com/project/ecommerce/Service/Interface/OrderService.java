package com.project.ecommerce.Service.Interface;

import com.project.ecommerce.Model.Orders;

import java.util.List;

public interface OrderService {

    Orders placeNewOrderFromCart(String username);

    List<Orders> findOrdersByUsername(String username);
}
