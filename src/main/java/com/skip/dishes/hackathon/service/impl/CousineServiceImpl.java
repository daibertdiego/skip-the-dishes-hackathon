package com.skip.dishes.hackathon.service.impl;

import com.skip.dishes.hackathon.model.Cousine;
import com.skip.dishes.hackathon.repository.CousineRepository;
import com.skip.dishes.hackathon.service.CousineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class CousineServiceImpl implements CousineService {

    @Autowired
    CousineRepository cousineRepository;

    @Override
    public Cousine save(Cousine cousine) throws Exception {
        return cousineRepository.save(cousine);
    }

    @Override
    public List<Cousine> search(String search) throws Exception {
        return cousineRepository.findBySearch(search);
    }

    @Override
    public List<Cousine> findAll() throws Exception {
        return cousineRepository.findAll();
    }

    @Override
    public List<Cousine> findBySearch(String searchText) throws Exception {
        return cousineRepository.findBySearch(searchText);
    }
}
