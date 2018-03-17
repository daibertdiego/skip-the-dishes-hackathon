package com.skip.dishes.hackathon.service;

import com.skip.dishes.hackathon.model.Store;

import java.util.List;

public interface StoreService {
    Store save(Store store) throws Exception;

    List<Store> findByCousineId(Long cousineId) throws Exception;
}
