package com.projects.foodonlinecliapp.service;

import com.projects.foodonlinecliapp.exceptions.OrderAlreadyExistsException;
import com.projects.foodonlinecliapp.exceptions.OrderNotFoundException;
import com.projects.foodonlinecliapp.model.Order;

import java.util.List;

public interface OrderService {
    Order save(Order order) throws OrderAlreadyExistsException;

    List<Order> getOrderList();

    Order getOrderById(String id) throws OrderNotFoundException;
}
