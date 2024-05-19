package com.example.grossbuh.repository;

import com.example.grossbuh.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query (value = "SELECT c FROM Customer c WHERE c.surname = :surname")
    Optional<Customer> findBySurname(String surname);
}
