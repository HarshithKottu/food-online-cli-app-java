package com.projects.foodonlinecliapp.controller;

import com.projects.foodonlinecliapp.exceptions.CustomerAlreadyExistsException;
import com.projects.foodonlinecliapp.model.Customer;
import com.projects.foodonlinecliapp.service.CustomerServiceImpl;

public class CustomerController {
    private final CustomerServiceImpl customerService;

    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    public Customer save(Customer customer) throws CustomerAlreadyExistsException {
        return this.customerService.save(customer);
    }
}
