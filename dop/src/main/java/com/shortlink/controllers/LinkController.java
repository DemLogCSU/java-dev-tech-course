package com.shortlink.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LinkController {
    @GetMapping("/")
    public String list(Model model){
        return "index";
    }
    @GetMapping("/add")
    public String add(Model model){

        return "add-link";
    }
    @GetMapping("/update/{id}")
    public String update(Model model){

        return "update-link";
    }
}
