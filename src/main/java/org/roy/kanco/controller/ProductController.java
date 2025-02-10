package org.roy.kanco.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.roy.kanco.dto.ProductDTO;
import org.roy.kanco.model.Product;
import org.roy.kanco.repository.CategoryRepository;
import org.roy.kanco.repository.ProductRepository;
import org.roy.kanco.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.roy.kanco.model.*;
import org.springframework.http.MediaType;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductService productService;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductController(ProductRepository productRepository, ProductService productService, CategoryRepository categoryRepository){

        this.productRepository = productRepository;
        this.productService = productService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Product> uploadProduct(
            @RequestParam("name") String name,
            @RequestParam("price") Double price,
            @RequestParam("image") MultipartFile image,
            @RequestParam("category") String type

    ){

        try {
            Product product;
            switch (type.toUpperCase()) {
                case "CEMENT":
                    product = new Cement();
                    break;
                case "PAINT":
                    product = new Paint();
                    break;
                case "BLOCKS":
                    product = new Blocks();
                    break;
                case "HARDWARE":
                    product = new Hardware();
                    break;
                default:
                    return ResponseEntity.badRequest().build();

            }

            Category category = categoryRepository.findByName(type).orElseThrow();
            product.setCategory(category);
            product.setName(name);
            product.setPrice(price);
            product.setCreatedAt(LocalDateTime.now());
            product.setUpdatedAt(LocalDateTime.now());
            product.setStock(0);

            Product savedProduct = productService.saveProduct(image, product);
            return ResponseEntity.ok(savedProduct);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getProductImage(@PathVariable Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getData() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .contentLength(product.getData().length)
                .header("Cache-Control","max-age=3600")
                // Change based on image type
                .body(product.getData());
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Product> updateProduct(@RequestParam("image") MultipartFile image,
                                                 @PathVariable Integer id,
                                                 @RequestParam("updatedProduct") String updatedProductJson) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        ProductDTO updatedProduct = mapper.readValue(updatedProductJson, ProductDTO.class);
        Product product = productService.updateProduct(id, updatedProduct, image);

        return ResponseEntity.ok(product);
    }

}
