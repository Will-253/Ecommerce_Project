package com.project.ecommerce.Controller;

import com.project.ecommerce.DTO.CartItemRequestDTO;
import com.project.ecommerce.DTO.CartItemResponseDTO;
import com.project.ecommerce.Model.CartItem;
import com.project.ecommerce.Service.Interface.CartService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<List<CartItemResponseDTO>> getAllCartItems(Principal principal, Pageable pageable) {

        Page<CartItemResponseDTO> page = cartService.getCartItems(
                principal.getName(), PageRequest.of(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        pageable.getSortOr(Sort.by(Sort.Direction.ASC, "quantity"))
                ));

        return ResponseEntity.ok(page.getContent());
    }

    @PostMapping
    public ResponseEntity<CartItem> addItemToCart(
            @RequestBody CartItemRequestDTO newCartItem, Principal principal) {

        return ResponseEntity.ok(cartService.addItemToCart(newCartItem, principal.getName()));

    }

    @PutMapping("{id}")
    public ResponseEntity<CartItemResponseDTO> updateCartItem(
            @PathVariable Long id, Principal principal, @RequestBody CartItemRequestDTO requestDTO){

        return ResponseEntity.ok(cartService.updateItemInCart(id,principal.getName(),requestDTO));
    }

    @DeleteMapping
    public ResponseEntity<List<Void>> deleteAllCartItems(Principal principal) {

        cartService.deleteAllItems(principal.getName());

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCartItemById(@PathVariable Long id, Principal principal) {

        cartService.deleteCartItem(id, principal);

        return ResponseEntity.noContent().build();
    }

}

