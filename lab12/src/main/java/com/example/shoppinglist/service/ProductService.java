package com.example.shoppinglist.service;

import com.example.shoppinglist.models.Product;
import com.example.shoppinglist.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public Iterable<Product> findAllProducts() {
        return productRepository.findAll();
    }
    public Product save(String name) {
        Product product = new Product(productRepository.getCount()+1, name);
        productRepository.save(product);
        return product;
    }
    public Long delete(Long id){
        productRepository.delete(id);
        return (long)0;
    }
    public void update(Long id){
        productRepository.update(id);
    }
    public Product getId(Long id){
        return productRepository.findById(id);
    }
}
