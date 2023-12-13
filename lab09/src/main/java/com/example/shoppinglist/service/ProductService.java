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
    public void save(String name) {
        productRepository.save(new Product(productRepository.getCount()+1, name));
    }
    public void delete(Long id){
        productRepository.delete(id);
    }
    public void update(Long id){
        productRepository.update(id);
    }
}
