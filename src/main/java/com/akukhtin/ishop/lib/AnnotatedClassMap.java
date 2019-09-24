package com.akukhtin.ishop.lib;

import com.akukhtin.ishop.Factory;
import com.akukhtin.ishop.dao.BucketDao;
import com.akukhtin.ishop.dao.ItemDao;
import com.akukhtin.ishop.dao.OrderDao;
import com.akukhtin.ishop.dao.UserDao;
import com.akukhtin.ishop.service.BucketService;
import com.akukhtin.ishop.service.ItemService;
import com.akukhtin.ishop.service.OrderService;
import com.akukhtin.ishop.service.UserService;

import java.util.HashMap;
import java.util.Map;

public class AnnotatedClassMap {
    private static final Map<Class, Object> classMap = new HashMap<>();

    static {
        classMap.put(ItemDao.class, Factory.getItemDao());
        classMap.put(BucketDao.class, Factory.getBucketDao());
        classMap.put(OrderDao.class, Factory.getOrderDao());
        classMap.put(UserDao.class, Factory.getUserDao());
        classMap.put(ItemService.class, Factory.getItemService());
        classMap.put(BucketService.class, Factory.getBucketService());
        classMap.put(OrderService.class, Factory.getOrderService());
        classMap.put(UserService.class, Factory.getUserService());
    }

    public static Object getImplementation(Class interfaceClass) {
        return classMap.get(interfaceClass);
    }
}
