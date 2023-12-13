package com.example.shoppinglist.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private boolean isPurchased;
    public Product(){

    }
    public Product(Long id, String name){
        this.id = id;
        this.name = name;
        this.isPurchased = false;
    }


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPurchased(boolean purchased) {
        isPurchased = purchased;
    }

    public boolean getIsPurchased() {
        return isPurchased;
    }
}
