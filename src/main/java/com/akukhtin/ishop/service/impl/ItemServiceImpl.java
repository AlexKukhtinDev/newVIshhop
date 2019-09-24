package com.akukhtin.ishop.service.impl;

import com.akukhtin.ishop.dao.ItemDao;
import com.akukhtin.ishop.lib.Inject;
import com.akukhtin.ishop.lib.Service;
import com.akukhtin.ishop.model.Item;
import com.akukhtin.ishop.service.ItemService;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Inject
    private static ItemDao itemDao;

    @Override
    public Item create(Item item) {
        return itemDao.create(item);
    }

    @Override
    public Item get(Long id) {
        return itemDao.get(id);
    }

    @Override
    public List<Item> getAll() {
        return itemDao.getAll();
    }

    @Override
    public Item update(Item item) {
        return itemDao.update(item);
    }

    @Override
    public Item delete(Long id) {
        return itemDao.delete(id);
    }

    @Override
    public Item deleteByItem(Item item) {
        return itemDao.deleteByItem(item);
    }
}
