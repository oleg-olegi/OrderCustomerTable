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

    @GetMapping("/get_all")
    @ResponseBody
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customerList = customerService.getAllCustomers();
        return ResponseEntity.ok(customerList);
    }

    @GetMapping("/update")
    public ResponseEntity<String> updateCustomer(@RequestParam int id,
                                                 @RequestParam String name,
                                                 @RequestParam String surname,
                                                 @RequestParam String phone) {
        Customer updatedCustomer = new Customer(surname, name, phone);
        customerService.updateCustomer(id, updatedCustomer);
        return ResponseEntity.ok("Заказчик успешно обновлен");
    }

    @GetMapping("/delete")
    public ResponseEntity<String> deleteCustomer(@RequestParam int customer_Id) {
        customerService.deleteCustomer(customer_Id);
        return ResponseEntity.ok("Заказчик успешно удален");
    }
}
