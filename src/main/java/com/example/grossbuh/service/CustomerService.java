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
        if (customerRepository.findById(customerId).isPresent()) {
            return customerRepository.findById(customerId).get();
        }
        throw new CustomerNotFoundException("Заказчик с указанным ID не найден");
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

    public void updateCustomer(int id, String name, String surname, String phone) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer existingCustomer = optionalCustomer.get();
            boolean needsUpdate = false;
            if (name != null && !name.trim().isEmpty()) {
                existingCustomer.setName(name);
                needsUpdate = true;
            }
            if (surname != null && !surname.trim().isEmpty()) {
                existingCustomer.setSurname(surname);
                needsUpdate = true;
            }
            if (phone != null && !phone.trim().isEmpty()) {
                existingCustomer.setPhone(phone);
                needsUpdate = true;
            }
            if (needsUpdate) {
                customerRepository.save(existingCustomer);
            }
        }
    }
}
