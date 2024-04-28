package com.example.grossbuh.service;

import com.example.grossbuh.exceptions.CustomerNotFoundException;
import com.example.grossbuh.model.Customer;
import com.example.grossbuh.model.Orders;
import com.example.grossbuh.model.StatusEnum;
import com.example.grossbuh.repository.CustomerRepository;
import com.example.grossbuh.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    private final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    public Orders createOrder(int customerId, short amount, String date, String link, byte[] photo, BigDecimal prepaid,
                              StatusEnum status, BigDecimal sumOrder) throws IOException {
        Optional<Customer> customerOptional = customerRepository.findById(Integer.toString(customerId));
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            logger.info("Найден заказчик {} ", customer);

            // Создаем превью для фото
            byte[] preview = generateImagePreview(photo);

            // Создаем заказ с полученными данными
            Orders order = new Orders(amount, date, link, preview, prepaid, status, sumOrder);
            logger.info("Создан заказ {}", order);
            //устанавливаем значение customer в таблицу
            order.setCustomer(customer);
            order.setCustomerSurname(customer.getSurname());
            // Сохраняем заказ
            return orderRepository.save(order);
        } else {
            // Обработка случая, когда заказчика с указанным ID нет в базе данных
            throw new CustomerNotFoundException("Customer with ID " + customerId + " not found");
        }
    }

    public List<Orders> getOrdersByCustomerSurname(String surname) {
        return orderRepository.findByCustomerSurname(surname)
                .stream()
                .toList();
    }

    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    private byte[] generateImagePreview(byte[] imageBytes) throws IOException {
        try (InputStream is = new ByteArrayInputStream(imageBytes);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            // Читаем изображение из массива байт
            BufferedImage image = ImageIO.read(bis);

            // Вычисляем высоту превью, сохраняя соотношение сторон
            int previewHeight = image.getHeight() * 100 / image.getWidth();

            // Создаем новое изображение (превью) с шириной 100 и рассчитанной высотой
            BufferedImage preview = new BufferedImage(100, previewHeight, image.getType());
            Graphics2D graphics = preview.createGraphics();

            // Рисуем изображение на превью и освобождаем ресурсы
            graphics.drawImage(image, 0, 0, 100, previewHeight, null);
            graphics.dispose();

            // Записываем превью в ByteArrayOutputStream с использованием того
            // же формата, что и исходное изображение
            ImageIO.write(preview, "jpg", baos);

            // Возвращаем массив байт превью
            return baos.toByteArray();
        } catch (IOException e) {
            throw e;
        }
    }
}
