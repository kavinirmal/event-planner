package com.example.hakunamatata.dto;

public class OrderDto {

    String order_name;
    String date;

    public OrderDto(String order_name, String date) {
        this.order_name = order_name;
        this.date = date;
    }

    public String getOrder_name() {
        return order_name;
    }

    public void setOrder_name(String order_name) {
        this.order_name = order_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
