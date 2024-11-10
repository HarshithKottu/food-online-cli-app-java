package com.projects.foodonlinecliapp.repository;

import com.projects.foodonlinecliapp.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRepository {
    private List<Order> orderList;

    public OrderRepository() {
        this.orderList = new ArrayList<>();
    }

    public Order save(Order order) {
        this.orderList.add(order);
        return order;
    }

    public List<Order> getOrderList() {
        return this.orderList;
    }

    public Optional<Order> getOrderById(String id) {
        return this.orderList.stream().filter(order -> order.getId().equals(id)).findFirst();
    }
}
