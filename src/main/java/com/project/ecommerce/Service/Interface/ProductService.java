package com.project.ecommerce.Service.Interface;

import com.project.ecommerce.DTO.ProductDTO;
import com.project.ecommerce.Model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


public interface ProductService {

    Page<ProductDTO> findAllProducts(PageRequest pageRequest);

    ProductDTO findProductById(Long id);

    Product createProduct(ProductDTO productDTO);

    void deleteProductById(Long id);

    ProductDTO updateProduct(Long id, ProductDTO productDTO);


}
