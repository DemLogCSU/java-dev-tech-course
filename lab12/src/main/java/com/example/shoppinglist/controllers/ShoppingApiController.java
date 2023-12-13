package com.example.shoppinglist.controllers;

import com.example.shoppinglist.models.Product;
import net.minidev.json.JSONObject;
import com.example.shoppinglist.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ShoppingApiController {
    @Autowired
    ProductService productService;
    @GetMapping("/")
    private Iterable<Product> list() {
        return productService.findAllProducts();
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    private ResponseEntity<String> addProduct(@RequestBody JSONObject jsonProduct) {

        var name = jsonProduct.get("product-name").toString();
        productService.save(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    private void delete(@PathVariable("id") Long id) {
        productService.delete(id);
    }
    @PutMapping("/{id}")
    private void update(@PathVariable("id") Long id) {
        productService.update(id);
    }
    @GetMapping("/{id}")
    private Product getById(@PathVariable("id") Long id) {
        return productService.getId(id);
    }
}
