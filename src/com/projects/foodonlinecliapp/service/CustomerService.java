package com.projects.foodonlinecliapp.service;

import com.projects.foodonlinecliapp.exceptions.CustomerAlreadyExistsException;
import com.projects.foodonlinecliapp.exceptions.CustomerNotFoundException;
import com.projects.foodonlinecliapp.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer save(Customer customer) throws CustomerAlreadyExistsException;

    List<Customer> getAllCustomers();

    Customer getCustomerById(String id) throws CustomerNotFoundException;

    Customer update(Customer customer);

    void delete(String id) throws CustomerNotFoundException;

    Customer validateCustomerLogin(String email, String password) throws CustomerNotFoundException;

    void setCurrentLoggedInCustomer(Customer customer);

    Customer getCurrentLoggedInCustomer();
}
