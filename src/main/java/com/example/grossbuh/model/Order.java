package com.example.grossbuh.model;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;

@Component
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "links")
    private String link;
    @Column(name = "amount")
    private short amount;
    @Column(name = "sum of order")
    private BigDecimal sumOfOrder;
    @Column(name = "prepayment")
    private BigDecimal prepayment;
    @Column(name = "photoData")

    private byte[] photoData;
    @Column(name = "date of order")
    private String date;
    @Column(name = "status")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;


    public Order() {
    }

    public Order(Customer customer, short amount, String date, String link, byte[] photoData,
                 BigDecimal prepaid, boolean status, BigDecimal sumOrder) {
        this.customer = customer;
        this.amount = amount;
        this.date = date;
        this.link = link;
        this.photoData = photoData;
        this.prepayment = prepaid;
        this.status = status;
        this.sumOfOrder = sumOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id
                && amount == order.amount
                && status == order.status
                && Objects.equals(link, order.link)
                && Objects.equals(sumOfOrder, order.sumOfOrder)
                && Objects.equals(prepayment, order.prepayment)
                && Arrays.equals(photoData, order.photoData)
                && Objects.equals(date, order.date);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, link, amount, sumOfOrder, prepayment, date, status);
        result = 31 * result + Arrays.hashCode(photoData);
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public short getAmount() {
        return amount;
    }

    public void setAmount(short amount) {
        this.amount = amount;
    }

    public BigDecimal getSumOfOrder() {
        return sumOfOrder;
    }

    public void setSumOfOrder(BigDecimal sumOfOrder) {
        this.sumOfOrder = sumOfOrder;
    }

    public BigDecimal getPrepayment() {
        return prepayment;
    }

    public void setPrepayment(BigDecimal prepayment) {
        this.prepayment = prepayment;
    }

    public byte[] getPhotoData() {
        return photoData;
    }

    public void setPhotoData(byte[] photoData) {
        this.photoData = photoData;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
