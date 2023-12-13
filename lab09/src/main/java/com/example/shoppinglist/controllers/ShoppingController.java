package com.example.shoppinglist.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ShoppingController {

    @GetMapping("/list")
    public String list(Model model){
        return "shop-list";
    }
    @GetMapping("/add")
    public String greeting(Model model){

        return "add-product";
    }

}
