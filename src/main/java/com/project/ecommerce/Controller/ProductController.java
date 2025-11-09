package com.project.ecommerce.Controller;

import com.project.ecommerce.DTO.ProductDTO;
import com.project.ecommerce.Model.Product;
import com.project.ecommerce.Service.Interface.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDTO> getAllProducts(){
        return productService.findAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Long id){
        return productService.findProductById(id);
    }

    @PostMapping
    public void createNewProduct(@RequestBody ProductDTO productDTO){
        productService.createProduct(productDTO);
    }

}
