package com.skip.dishes.hackathon.resource;

import com.skip.dishes.hackathon.service.CousineService;
import com.skip.dishes.hackathon.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/Cousine")
public class CousineResource {

    @Autowired
    CousineService cousineService;

    @Autowired
    StoreService storeService;

    @GetMapping
    public ResponseEntity findAll() {
        try {
            return new ResponseEntity(cousineService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new ArrayList<>(), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/search/{searchText}")
    public ResponseEntity findBySearch(@PathVariable("searchText") String searchText) {
        try {
            return new ResponseEntity(cousineService.findBySearch(searchText), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new ArrayList<>(), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/search/{cousineID}/stores")
    public ResponseEntity findBySearch(@PathVariable("cousineID") Long cousineId) {
        try {
            return new ResponseEntity(storeService.findByCousineId(cousineId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new ArrayList<>(), HttpStatus.OK);
        }
    }


}
