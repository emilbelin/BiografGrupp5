package com.example.application.Backend.model;

public class BookingObject {
    private Customer customer;
    private Chair chair;
    private Row row;
    private PaymentMethod paymentMethod;

    public BookingObject(Customer customer, Chair chair, Row row, PaymentMethod paymentMethod) {
        this.customer = customer;
        this.chair = chair;
        this.row = row;
        this.paymentMethod = paymentMethod;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Chair getChair() {
        return chair;
    }

    public void setChair(Chair chair) {
        this.chair = chair;
    }

    public Row getRow() {
        return row;
    }

    public void setRow(Row row) {
        this.row = row;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
