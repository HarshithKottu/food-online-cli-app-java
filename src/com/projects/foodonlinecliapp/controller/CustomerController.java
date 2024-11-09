package com.projects.foodonlinecliapp.controller;

import com.projects.foodonlinecliapp.exceptions.CustomerAlreadyExistsException;
import com.projects.foodonlinecliapp.exceptions.CustomerNotFoundException;
import com.projects.foodonlinecliapp.model.Customer;
import com.projects.foodonlinecliapp.service.CustomerServiceImpl;

import java.util.List;

public class CustomerController {
    private final CustomerServiceImpl customerService;

    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    public Customer save(Customer customer) throws CustomerAlreadyExistsException {
        return this.customerService.save(customer);
    }

    public Customer validateCustomerLogin(String email, String password) throws CustomerNotFoundException {
        return this.customerService.validateCustomerLogin(email, password);
    }

    public void setCurrentLoggedInCustomer(Customer customer) {
        this.customerService.setCurrentLoggedInCustomer(customer);
    }

    public Customer getCustomerById(String id) throws CustomerNotFoundException {
        return this.customerService.getCustomerById(id);
    }

    public List<Customer> getCustomers() {
        return this.customerService.getAllCustomers();
    }

    public Customer getUpdate(Customer customer) throws CustomerNotFoundException {
        return this.customerService.update(customer);
    }

    public void delete(String id) throws CustomerNotFoundException {
        this.customerService.delete(id);
    }
}
