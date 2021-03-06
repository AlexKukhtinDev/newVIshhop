package com.akukhtin.ishop.dao.impl;

import com.akukhtin.ishop.dao.BucketDao;
import com.akukhtin.ishop.dao.Storage;
import com.akukhtin.ishop.lib.Dao;
import com.akukhtin.ishop.model.Bucket;

import java.util.NoSuchElementException;
import java.util.Optional;

@Dao
public class BucketDaoImpl implements BucketDao {

    @Override
    public Optional<Bucket> create(Bucket bucket) {
        Storage.buckets.add(bucket);
        return Optional.ofNullable(bucket);
    }

    @Override
    public Optional<Bucket> get(Long bucketId) {
        return Storage.buckets
                .stream()
                .filter(i -> i.getId().equals(bucketId))
                .findFirst();
    }

    @Override
    public Optional<Bucket> getByUser(Long userId) {
        return Storage.buckets
                .stream()
                .filter(i -> i.getUser().getId().equals(userId))
                .findFirst();
    }

    @Override
    public Optional<Bucket> update(Bucket bucket) {
        for (int i = 0; i < Storage.buckets.size(); i++) {
            if (Storage.buckets.get(i).getId().equals(bucket.getId())) {
                Storage.buckets.set(i, bucket);
                return Optional.of(bucket);
            }
        }
        throw new NoSuchElementException("Can't find item" + bucket.getId());
    }

    @Override
    public void delete(Long id) {
        Storage.buckets.removeIf(s -> s.getId().equals(id));
    }

    @Override
    public Optional<Bucket> addItem(Long bucketId, Long itemId) {
        return Optional.empty();
    }

    @Override
    public Optional<Bucket> deleteItem(Long bucketId, Long itemId) {
        return Optional.empty();
    }

    @Override
    public Optional<Bucket> clear(Long bucketId) {
        return Optional.empty();
    }
}
