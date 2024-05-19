package com.example.grossbuh.repository;


import com.example.grossbuh.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Orders, Integer> {
    @Query("SELECT o FROM Orders o JOIN o.customer c WHERE c.surname = :surname")
    List<Orders> findByCustomerSurname(String surname);

    @Modifying
    @Query("DELETE FROM Orders o WHERE o.customer.id = :customerId")
    void deleteByCustomerId(int customerId);
}
