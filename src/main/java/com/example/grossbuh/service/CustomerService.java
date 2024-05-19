package com.example.grossbuh.service;

import com.example.grossbuh.exceptions.CustomerNotFoundException;
import com.example.grossbuh.model.Customer;
import com.example.grossbuh.repository.CustomerRepository;
import com.example.grossbuh.repository.OrderRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    public CustomerService(CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    public Customer createCustomer(String surname, String name, String phone) {

        Customer customer = new Customer(surname, name, phone);

        return customerRepository.save(customer);
    }

    public Customer getCustomerById(int customerId) {
        return customerRepository.findById(customerId).get();
    }

    @Transactional
    public void deleteCustomer(int customerId) {
        if (customerRepository.existsById(customerId)) {
            orderRepository.deleteByCustomerId(customerId);
            customerRepository.deleteById(customerId);
        } else {
            throw new CustomerNotFoundException("Заказчик с указанным ID не найден");
        }
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void updateCustomer(int id, Customer customer) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer existedCustomer = optionalCustomer.get();
            existedCustomer.setName(customer.getName());
            existedCustomer.setSurname(customer.getSurname());
            existedCustomer.setPhone(customer.getPhone());
            customerRepository.save(existedCustomer);
        } else {
            throw new CustomerNotFoundException("Заказчик с указанным ID не найден");
        }
    }
}

