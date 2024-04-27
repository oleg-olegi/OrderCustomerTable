package com.example.grossbuh.controller;

import com.example.grossbuh.model.Order;
import com.example.grossbuh.service.OrderService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RequestMapping("/order")
@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Order> createCustomer(
            @RequestParam("customerId") int customerId,
            @RequestParam("Количество") short amount,
            @RequestParam("Дата заказа") @DateTimeFormat(pattern = "dd-MM-yyyy") String date,
            @RequestParam("Ссылка на товар") String link,
            @RequestParam("Фото") MultipartFile photoFile,
            @RequestParam("Предоплата, руб") BigDecimal prepaid,
            @RequestParam("Сумма заказа, руб") BigDecimal sumOrder,
            @RequestParam("Статус заказа") boolean status) throws IOException {

        Order order = orderService.createOrder(customerId, amount, date, link, photoFile.getBytes(), prepaid, status, sumOrder);
        return ResponseEntity.ok(order);
    }

    @GetMapping(value = "/get-orders")
    public ResponseEntity<List<Order>> getOrdersBySurname(@RequestParam("Фамилия") String surname) {
        List<Order> orders = orderService.getOrdersByCustomerSurname(surname);
        return ResponseEntity.ok(orders);
    }

    @GetMapping(value = "/get-all-orders")
    public ResponseEntity<List<Order>> getOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
}