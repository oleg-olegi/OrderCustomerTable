package com.example.grossbuh.controller;

import com.example.grossbuh.model.Customer;
import com.example.grossbuh.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/customer")
@RestController
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(@RequestParam("Фамилия") String surname,
                                                   @RequestParam("Имя") String name,
                                                   @RequestParam("Телефон") String phone) {
        Customer customer = customerService.createCustomer(surname, name, phone);
        return ResponseEntity.ok(customer);
    }

    @GetMapping("{customer_Id}")
    @ResponseBody
    public ResponseEntity<Customer> getCustomersByCustomerId(@PathVariable int customer_Id) {
        return ResponseEntity.ok(customerService.getCustomerById(customer_Id));
    }
}
