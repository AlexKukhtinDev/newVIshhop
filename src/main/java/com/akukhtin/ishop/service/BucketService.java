package com.akukhtin.ishop.service;

import com.akukhtin.ishop.model.Bucket;
import com.akukhtin.ishop.model.Item;

import java.util.List;

public interface BucketService {

    Bucket addItem(Long bucketId, Long itemId);

    Bucket deleteItem(Long bucketId, Long itemId);

    Bucket clear(Long bucketId);

    List<Item> getAllItems(Long bucketId);

    Bucket create(Bucket bucket);

    Bucket get(Long id);

    Bucket getByUser(Long userId);

    Bucket update(Bucket bucket);

    Bucket delete(Long id);

    Bucket deleteByBucket(Bucket bucket);
}
