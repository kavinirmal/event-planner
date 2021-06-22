package com.dreams.hakunamatata.dto;

public class OrderDto {

    String order_name,description,lesson_id,order;

    public OrderDto(String order_name, String description, String lesson_id, String order) {
        this.order_name = order_name;
        this.description = description;
        this.lesson_id = lesson_id;
        this.order = order;
    }

    public String getOrder_name() {
        return order_name;
    }

    public void setOrder_name(String order_name) {
        this.order_name = order_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(String lesson_id) {
        this.lesson_id = lesson_id;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
