package com.akukhtin.ishop.model;

import com.akukhtin.ishop.idgenerator.ItemIdGenerator;

public class Item {

    private Long id;
    private String name;
    private String model;
    private Double price;

    public Item(Long id) {
        this.id = id;
    }

    public Item() {
        this.id = ItemIdGenerator.getGeneratedId();
    }

    public Item(String name, String model, Double price) {
        this.id = ItemIdGenerator.getGeneratedId();
        this.name = name;
        this.model = model;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
