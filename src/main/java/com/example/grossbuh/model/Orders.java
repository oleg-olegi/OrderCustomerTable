package com.example.grossbuh.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

@Component
@Entity
@Table(name = "orders")

public class Orders {
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
    @JsonProperty
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
    @Column(name = "customer_surname")
    private String customerSurname;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    @Cascade(org.hibernate.annotations.CascadeType.REMOVE)
    private Customer customer;

    public Orders() {
    }

    public Orders(Customer customer) {
        this.customer = customer;
    }

    public Orders(short amount, String date, String link, byte[] photoData,
                  BigDecimal prepaid, StatusEnum status, BigDecimal sumOrder) {
        this.amount = amount;
        this.date = date;
        this.link = link;
        this.photoData = photoData;
        this.prepayment = prepaid;
        this.status = status;
        this.sumOfOrder = sumOrder;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", link='" + link + '\'' +
                ", amount=" + amount +
                ", sumOfOrder=" + sumOfOrder +
                ", prepayment=" + prepayment +
                ", photoData=" + Arrays.toString(photoData) +
                ", date='" + date + '\'' +
                ", status='" + status + '\'' +
                ", customerSurname='" + customerSurname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return id == orders.id
                && amount == orders.amount
                && Objects.equals(status, orders.status)
                && Objects.equals(link, orders.link)
                && Objects.equals(sumOfOrder, orders.sumOfOrder)
                && Objects.equals(prepayment, orders.prepayment)
                && Arrays.equals(photoData, orders.photoData)
                && Objects.equals(date, orders.date);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, link, amount, sumOfOrder, prepayment, date, status);
        result = 31 * result + Arrays.hashCode(photoData);
        return result;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getCustomerSurname() {
        return customerSurname;
    }

    public void setCustomerSurname(String customerSurname) {
        this.customerSurname = customerSurname;
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

    public StatusEnum isStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}
