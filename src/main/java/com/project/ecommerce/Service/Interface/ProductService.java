package com.project.ecommerce.Service.Interface;

import com.project.ecommerce.DTO.ProductDTO;
import com.project.ecommerce.Model.Product;

import java.util.List;

public interface ProductService {
    List<ProductDTO> findAllProducts();

    ProductDTO findProductById(Long id);

    void createProduct(ProductDTO productDTO);

}
