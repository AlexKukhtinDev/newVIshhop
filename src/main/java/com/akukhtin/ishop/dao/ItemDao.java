package com.akukhtin.ishop.dao;

import com.akukhtin.ishop.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemDao {

    Optional<Item> create(Item item);

    Optional<Item> get(Long id);

    List<Item> getAll();

    Optional<Item> update(Item item);

    void delete(Long id);

    Optional<Item> deleteByItem(Item item);
}
