package com.akukhtin.ishop.model;

import com.akukhtin.ishop.idgenerator.BucketIdGenerator;

import java.util.ArrayList;
import java.util.List;

public class Bucket {
    private Long id;
    private Long userId;
    private List<Item> items;

    public Bucket(User user) {
        this.id = BucketIdGenerator.getGeneratedId();
        this.userId = user.getId();
        items = new ArrayList<>();
    }

    public Bucket(Long userId) {
        this.id = BucketIdGenerator.getGeneratedId();
        this.userId = userId;
        items = new ArrayList<>();
    }

    public void clearItems() {
        items.clear();
    }

    public void deleteItem(Long id) {
        items.removeIf(s -> s.getId().equals(id));
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
