package com.projects.foodonlinecliapp.controller;

import com.projects.foodonlinecliapp.exceptions.OrderAlreadyExistsException;
import com.projects.foodonlinecliapp.exceptions.OrderNotFoundException;
import com.projects.foodonlinecliapp.model.Order;
import com.projects.foodonlinecliapp.service.OrderServiceImpl;

import java.util.List;

public class OrderController {
    private final OrderServiceImpl orderService;

    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    public Order saveOrder(Order order) throws OrderAlreadyExistsException {
        return this.orderService.save(order);
    }

    public List<Order> getOrderList() {
        return this.orderService.getOrderList();
    }

    public Order getOrderById(String id) throws OrderNotFoundException {
        return this.orderService.getOrderById(id);
    }
}
