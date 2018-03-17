package com.skip.dishes.hackathon.service;

import com.skip.dishes.hackathon.model.Customer;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

public interface CustomerService {
    Customer save(Customer customer) throws Exception;

    Customer loadUserByUsername(String username) throws Exception;
}
