package com.project.ecommerce.Controller;

import com.project.ecommerce.DTO.ProductDTO;
import com.project.ecommerce.Model.Product;
import com.project.ecommerce.Service.Interface.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts(Pageable pageable) {

        Page<ProductDTO> page = productService.findAllProducts(
                PageRequest.of(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        pageable.getSortOr(Sort.by(Sort.Direction.ASC, "name"))
                ));

        return ResponseEntity.ok(page.getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findProductById(id));
    }

    @PostMapping
    public ResponseEntity<Void> createNewProduct(@RequestBody ProductDTO productDTO,
                                                 UriComponentsBuilder ucb) {

        Product savedProduct= productService.createProduct(productDTO);

        URI productLocation = ucb
                .path("/api/product/{id}")
                .buildAndExpand(savedProduct.getId())
                .toUri();
        return ResponseEntity.created(productLocation).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id) {

        productService.deleteProductById(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductDTO> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductDTO productDTO) {

        ProductDTO updatedProduct = productService.updateProduct(id,productDTO);

        return ResponseEntity.ok(updatedProduct);
    }

}
