package org.roy.kanco.service;

import org.roy.kanco.dto.ProductDTO;
import org.roy.kanco.model.Product;
import org.roy.kanco.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveProduct(MultipartFile file, Product product) throws IOException {

        product.setData(file.getBytes());
        return productRepository.save(product);
    }

    public byte[] getProductImage(Integer id){

        return productRepository.findById(id)
                .map(Product::getData)
                .orElse(null);

    }

    public Product updateProduct(Integer productId, ProductDTO updatedProductDTO, MultipartFile file) throws IOException {
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        if (updatedProductDTO.getName() != null) {
            product.setName(updatedProductDTO.getName());
        }
        if (updatedProductDTO.getPrice() != null) {
            product.setPrice(updatedProductDTO.getPrice());
        }
        if (updatedProductDTO.getStock() != null) {
            product.setStock(updatedProductDTO.getStock());
        }
        if (file != null && !file.isEmpty()) {
            product.setData(file.getBytes());
        }
        return productRepository.save(product);
    }

    public void deleteProduct(Integer productId) {
        productRepository.deleteById(productId);
    }

    public Product restockProduct(Integer productId, Integer quantity) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        product.setStock(product.getStock() + quantity);
        return productRepository.save(product);
    }


}
