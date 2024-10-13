package com.projects.foodonlinecliapp.util;

import com.projects.foodonlinecliapp.controller.CustomerController;
import com.projects.foodonlinecliapp.repository.CustomerRepository;
import com.projects.foodonlinecliapp.service.CustomerServiceImpl;

public class Factory {
    // static factory Methods
    public static CustomerRepository getCustomerRepository() {
        return new CustomerRepository();
    }

    public static CustomerServiceImpl getCustomerService() {
        return new CustomerServiceImpl(getCustomerRepository());
    }

    public static CustomerController getCustomerController() {
        return new CustomerController(getCustomerService());
    }
}
