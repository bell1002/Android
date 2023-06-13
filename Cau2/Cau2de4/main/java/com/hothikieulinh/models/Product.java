package com.hothikieulinh.models;

import java.io.Serializable;

public class Product implements Serializable {
    String Id;
    String Name;
    Double Price;

    public Product(String id, String name, Double price) {
        Id = id;
        Name = name;
        Price = price;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    @Override
    public String toString() {
        return
                "Ma san pham: " + Id + "\n" +
                "Ten san pham: " + Name + "\n" +
                "Gia ban ra: " + Price;
    }
}
