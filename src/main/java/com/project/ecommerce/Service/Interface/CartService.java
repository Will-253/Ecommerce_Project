package com.project.ecommerce.Service.Interface;

import com.project.ecommerce.DTO.CartItemRequestDTO;
import com.project.ecommerce.DTO.CartItemResponseDTO;
import com.project.ecommerce.Model.CartItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.security.Principal;

public interface CartService {

    Page<CartItemResponseDTO> getCartItems(String name, PageRequest pageRequest);

    CartItem addItemToCart(CartItemRequestDTO newCartItem, String name);

    CartItemResponseDTO updateItemInCart(Long id, String name, CartItemRequestDTO requestDTO);

    void deleteAllItems(String name);

    void deleteCartItem(Long id, Principal principal);
}
