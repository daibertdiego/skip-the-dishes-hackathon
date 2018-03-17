package com.skip.dishes.hackathon.service.impl;

import com.skip.dishes.hackathon.model.Customer;
import com.skip.dishes.hackathon.repository.CustomerRepository;
import com.skip.dishes.hackathon.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public Customer save(Customer customer) throws Exception {
        if (customerRepository.findOneByUsername(customer.getEmail()) == null) {
            customer.setPassword(encoder.encode(customer.getPassword()));
            return customerRepository.save(customer);
        } else {
            throw new Exception("Customer already exists!");
        }
    }

    @Override
    public Customer loadUserByUsername(String username) throws Exception {
        return customerRepository.findOneByUsername(username);
    }
}
