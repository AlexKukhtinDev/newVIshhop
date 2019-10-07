package com.akukhtin.ishop.service.impl;

import com.akukhtin.ishop.dao.BucketDao;
import com.akukhtin.ishop.dao.ItemDao;
import com.akukhtin.ishop.lib.Inject;
import com.akukhtin.ishop.lib.Service;
import com.akukhtin.ishop.model.Bucket;
import com.akukhtin.ishop.model.Item;
import com.akukhtin.ishop.service.BucketService;

import java.util.List;
import java.util.Optional;

@Service
public class BucketServiceImpl implements BucketService {

    @Inject
    private static BucketDao bucketDao;

    @Inject
    private static ItemDao itemDao;

    @Override
    public Optional<Bucket> addItem(Long bucketId, Long itemId) {
        return bucketDao.addItem(bucketId, itemId);
    }

    @Override
    public Optional<Bucket> deleteItem(Long bucketId, Long itemId) {
        return bucketDao.deleteItem(bucketId, itemId);
    }

    @Override
    public Optional<Bucket> clear(Long bucketId) {
        return bucketDao.clear(bucketId);
    }

    @Override
    public Optional<List<Item>> getAllItems(Long bucketId) {
        Optional<Bucket> bucket = bucketDao.get(bucketId);
        return Optional.ofNullable(bucket.get().getItems());
    }

    @Override
    public Optional<Bucket> create(Bucket bucket) {
        return bucketDao.create(bucket);
    }

    @Override
    public Optional<Bucket> get(Long id) {
        return bucketDao.get(id);
    }

    @Override
    public Optional<Bucket> getByUser(Long userId) {
        return bucketDao.getByUser(userId);
    }

    @Override
    public Optional<Bucket> update(Bucket bucket) {
        return bucketDao.update(bucket);
    }

    @Override
    public void delete(Long id) {
        bucketDao.delete(id);
    }
}
