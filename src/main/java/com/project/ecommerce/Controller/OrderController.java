package com.project.ecommerce.Controller;

import com.project.ecommerce.Model.Orders;
import com.project.ecommerce.Service.Interface.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Orders> PlaceOrder(Principal principal) {

        String username = principal.getName();

        Orders finalizedOrder = orderService.placeNewOrderFromCart(username);

        return new ResponseEntity<>(finalizedOrder, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Orders>> getOrdersByUser(Principal principal) {

        String username = principal.getName();

        List<Orders> allOrders = orderService.findOrdersByUsername(username);

        if (allOrders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(allOrders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orders> getOrdersById(@PathVariable Long id, Principal principal) {

        Orders order = orderService.findOrderByIdAndUsername(id,principal.getName());

        return ResponseEntity.ok().build();
    }


}
