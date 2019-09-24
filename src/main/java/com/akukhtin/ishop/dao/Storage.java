package com.akukhtin.ishop.dao;

import com.akukhtin.ishop.model.Bucket;
import com.akukhtin.ishop.model.Item;
import com.akukhtin.ishop.model.Order;
import com.akukhtin.ishop.model.User;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    public static final List<Item> items = new ArrayList<>();
    public static final List<Bucket> buckets = new ArrayList<>();
    public static final List<Order> orders = new ArrayList<>();
    public static final List<User> users = new ArrayList<>();
}
