package com.akukhtin.ishop.dao.impl;

import com.akukhtin.ishop.dao.ItemDao;
import com.akukhtin.ishop.dao.Storage;
import com.akukhtin.ishop.lib.Dao;
import com.akukhtin.ishop.model.Item;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Dao
public class ItemDaoImpl implements ItemDao {
    @Override
    public Optional<Item> create(Item item) {
        Storage.items.add(item);
        return Optional.ofNullable(item);
    }

    @Override
    public Optional<Item> get(Long id) {
        return Storage.items
                .stream()
                .filter(i -> i.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<List<Item>> getAll() {
        return Optional.of(Storage.items);
    }

    @Override
    public Optional<Item> update(Item item) {
        for (int i = 0; i < Storage.items.size(); i++) {
            if (Storage.items.get(i).getId().equals(item.getId())) {
                Storage.items.set(i, item);
                return Optional.of(item);
            }
        }
        throw new NoSuchElementException("Can't find item" + item.getName());
    }

    @Override
    public void delete(Long id) {
        Storage.items.removeIf(s -> s.getId().equals(id));
    }

    @Override
    public Optional<Item> deleteByItem(Item item) {
        Storage.items.removeIf(s -> s.equals(item));
        return Optional.ofNullable(item);
    }
}
