package com.project.ecommerce.DTO;

public class CartItemResponseDTO {

    private Long id;

    private int quantity;

    private Long productId;

    private String productName;

    private Double productPrice;

    private Long userId;

    public CartItemResponseDTO() {
    }

    public CartItemResponseDTO(Long id, int quantity, Long productId, String productName, Double productPrice, Long userId) {
        this.id = id;
        this.quantity = quantity;
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
