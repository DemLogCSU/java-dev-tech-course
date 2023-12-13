package com.example.shoppinglist.repository;

import com.example.shoppinglist.models.Product;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class  ProductRepository {
    private final Map<Long, Product> products = new HashMap<>(){{
        put((long)1, new Product((long)1, "apple"));
    }};
    public Iterable<Product> findAll(){
        return products.values();
    }
    public Product findById(long id){
        return products.get(id);
    }
    public void save(Product product){
        products.put(product.getId(), product);
    }
    public Long getCount(){
        return (long)products.size();
    }
    public void delete(Long id){
        products.remove(id);
    }
    public void update(Long id){
        products.get(id).setPurchased(!products.get(id).getIsPurchased());
    }
}
