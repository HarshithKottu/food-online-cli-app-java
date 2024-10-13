package com.projects.foodonlinecliapp.service;

import com.projects.foodonlinecliapp.exceptions.CustomerAlreadyExistsException;
import com.projects.foodonlinecliapp.model.Customer;

public interface CustomerService {
    public Customer save(Customer customer) throws CustomerAlreadyExistsException;
}
