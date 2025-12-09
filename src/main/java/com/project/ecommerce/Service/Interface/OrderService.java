package com.project.ecommerce.Service.Interface;

import com.project.ecommerce.Model.Orders;

public interface OrderService {

    Orders placeNewOrderFromCart(String username);
}
