package com.project.ecommerce.Service.Impl;

import com.project.ecommerce.DTO.CartItemRequestDTO;
import com.project.ecommerce.DTO.CartItemResponseDTO;
import com.project.ecommerce.Model.CartItem;
import com.project.ecommerce.Model.EcommerceUser;
import com.project.ecommerce.Model.Product;
import com.project.ecommerce.Repository.ProductRepository;
import com.project.ecommerce.Repository.ShoppingCartRepository;
import com.project.ecommerce.Repository.UserRepository;
import com.project.ecommerce.Service.Interface.ShoppingCartService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;



@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository cartRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    public ShoppingCartServiceImpl(ShoppingCartRepository cartRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Page<CartItemResponseDTO> getCartItems(String name, PageRequest pageRequest) {

        Page<CartItem> page = cartRepository.findAllByUser_Username(name,pageRequest);

        return page.map(this::convertToDTO);
    }

    @Override
    public CartItem addItemToCart(CartItemRequestDTO newCartItem, String name) {

        CartItem cartItem = convertToEntity(newCartItem,name);

        return cartRepository.save(cartItem);
    }

    @Override
    public CartItem updateItemInCart(Long id, String name, CartItemRequestDTO requestDTO) {
        return null;
    }


    private CartItemResponseDTO convertToDTO(CartItem cartItem) {

        CartItemResponseDTO cartItemResponseDTO = new CartItemResponseDTO();

        cartItemResponseDTO.setId(cartItem.getId());
        cartItemResponseDTO.setQuantity(cartItem.getQuantity());

        if (cartItem.getProduct() != null) {
            cartItemResponseDTO.setProductId(cartItem.getProduct().getId());
            cartItemResponseDTO.setProductName(cartItem.getProduct().getName());
            cartItemResponseDTO.setProductPrice(cartItem.getProduct().getPrice());
        }

        if (cartItem.getUser() != null) {
            cartItemResponseDTO.setUserId(cartItem.getUser().getId());
        }

        return cartItemResponseDTO;
    }

    private CartItem convertToEntity(CartItemRequestDTO requestDTO, String name) {

        Product product = productRepository.findById(requestDTO.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + requestDTO.getProductId()));

        EcommerceUser user = userRepository.findByUsername(name)
                .orElseThrow(() -> new IllegalArgumentException("User not found with username: " + name));

        CartItem cartItem = new CartItem();

        cartItem.setQuantity(requestDTO.getQuantity());
        cartItem.setProduct(product);
        cartItem.setUser(user);

        return cartItem;
    }
}