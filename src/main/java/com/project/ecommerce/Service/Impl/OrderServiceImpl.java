package com.project.ecommerce.Service.Impl;

import com.project.ecommerce.Model.*;
import com.project.ecommerce.Repository.CartRepository;
import com.project.ecommerce.Repository.OrderRepository;
import com.project.ecommerce.Repository.ProductRepository;
import com.project.ecommerce.Repository.UserRepository;
import com.project.ecommerce.Service.Interface.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    private final CartRepository cartRepository;

    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository,
                            UserRepository userRepository,
                            CartRepository cartRepository,
                            ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public Orders placeNewOrderFromCart(String username) {

        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));

        List<CartItem> cartItems = cartRepository.findByUser(user);

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cannot place New Order: Shopping cart is empty");
        }

        Orders newOrder = new Orders();
        newOrder.setUser(user);
        newOrder.setOrderDate(LocalDateTime.now());

        List<OrderItem> orderItems = new ArrayList<>();
        double totalPrice = 0.0;

        for (CartItem cartItem : cartItems) {
            Product product = cartItem.getProduct();
            int quantity = cartItem.getQuantity();

            Product currentProduct = productRepository.findById(product.getId())
                    .orElseThrow(() -> new RuntimeException("Product not found: " + product.getId()));

            double priceAtPurchase = currentProduct.getPrice();

            if (currentProduct.getStock() < quantity) {
                throw new RuntimeException("Insufficient stock for product " + currentProduct.getName());
            }

            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(quantity);
            orderItem.setProduct(currentProduct);
            orderItem.setPriceAtPurchase(priceAtPurchase);
            orderItem.setOrder(newOrder);


            orderItems.add(orderItem);
            totalPrice += priceAtPurchase * quantity;

            currentProduct.setStock(currentProduct.getStock() - quantity);

            productRepository.save(currentProduct);
        }

        newOrder.setOrderItems(orderItems);
        newOrder.setTotalPrice(totalPrice);

        Orders savedOrder = orderRepository.save(newOrder);

        cartRepository.deleteAll(cartItems);

        return savedOrder;
    }

    @Override
    public List<Orders> findOrdersByUsername(String username) {

        Users currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));

        return orderRepository.findByUser(currentUser);
    }
}
