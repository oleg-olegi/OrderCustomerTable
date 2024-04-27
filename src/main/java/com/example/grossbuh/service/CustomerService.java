package com.example.grossbuh.service;

import com.example.grossbuh.model.Customer;
import com.example.grossbuh.repository.CustomerRepository;

@org.springframework.stereotype.Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(String surname, String name, String phone) {

        Customer customer = new Customer(surname, name, phone);

        return customerRepository.save(customer);
    }
}
