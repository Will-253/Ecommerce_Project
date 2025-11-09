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
    @PreAuthorize("hasAuthority('ADMIN')")
    public Product createProduct(ProductDTO productDTO) {

        Product product = convertToEntity(productDTO);

        return productRepository.save(product);

    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteProductById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id:"));

        productRepository.delete(product);
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {

        Product existingProduct = productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found with id:"));

        existingProduct.setName(productDTO.getName());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setPrice(productDTO.getPrice());

        Product savedProduct = productRepository.save(existingProduct);

        return convertToDTO(savedProduct);
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
