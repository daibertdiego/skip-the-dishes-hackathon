package com.skip.dishes.hackathon.service;

import com.skip.dishes.hackathon.model.Cousine;

import java.util.List;

public interface CousineService {
    Cousine save(Cousine cousine) throws Exception;

    List<Cousine> search(String search) throws Exception;

    List<Cousine> findAll() throws Exception;

    List<Cousine> findBySearch(String searchText) throws Exception;
}
