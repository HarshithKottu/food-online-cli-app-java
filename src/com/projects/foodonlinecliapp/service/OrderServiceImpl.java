package com.projects.foodonlinecliapp.service;

import com.projects.foodonlinecliapp.exceptions.OrderAlreadyExistsException;
import com.projects.foodonlinecliapp.exceptions.OrderNotFoundException;
import com.projects.foodonlinecliapp.model.Order;
import com.projects.foodonlinecliapp.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order save(Order order) throws OrderAlreadyExistsException {
        Optional<Order> orderById = this.orderRepository.getOrderById(order.getId());
        if (orderById.isPresent())
            throw new OrderAlreadyExistsException("Order Already Exists with this ID: " + order.getId());
        return this.orderRepository.save(order);
    }

    @Override
    public List<Order> getOrderList() {
        return this.orderRepository.getOrderList();
    }

    @Override
    public Order getOrderById(String id) throws OrderNotFoundException {
        Optional<Order> orderById = this.orderRepository.getOrderById(id);
        if (orderById.isEmpty())
            throw new OrderNotFoundException("Order Not Found with given ID: " + id);
        return orderById.get();
    }
}
