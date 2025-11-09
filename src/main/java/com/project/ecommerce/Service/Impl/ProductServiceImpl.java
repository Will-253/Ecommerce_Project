package com.project.ecommerce.Service.Impl;

import com.project.ecommerce.DTO.ProductDTO;
import com.project.ecommerce.Exceptions.ResourceNotFoundException;
import com.project.ecommerce.Model.Product;
import com.project.ecommerce.Repository.ProductRepository;
import com.project.ecommerce.Service.Interface.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDTO> findAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public ProductDTO findProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id:"));

        return convertToDTO(product);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void createProduct(ProductDTO productDTO) {

        Product product = convertToEntity(productDTO);

      productRepository.save(product);

    }

    private ProductDTO convertToDTO(Product product) {
        return new ProductDTO(
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }

    private Product convertToEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());

        return product;
    }



}
