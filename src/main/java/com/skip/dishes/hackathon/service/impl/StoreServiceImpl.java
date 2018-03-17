package com.skip.dishes.hackathon.service.impl;

import com.skip.dishes.hackathon.model.Store;
import com.skip.dishes.hackathon.repository.StoreRepository;
import com.skip.dishes.hackathon.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Override
    public Store save(Store store) throws Exception {
        return storeRepository.save(store);
    }

    @Override
    public List<Store> findByCousineId(Long cousineId) throws Exception {
        return storeRepository.findByCousineId(cousineId);
    }
}
