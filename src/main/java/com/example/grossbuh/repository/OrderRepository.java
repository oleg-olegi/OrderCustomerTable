package com.example.grossbuh.repository;


import com.example.grossbuh.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, String> {
    @Query("SELECT o FROM Order o INNER JOIN o.customer c WHERE c.surname = :surname")
    List<Optional<Order>> findByCustomerSurname(String surname);
}
