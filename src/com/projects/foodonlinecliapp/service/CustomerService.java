package com.projects.foodonlinecliapp.service;

import com.projects.foodonlinecliapp.exceptions.CustomerAlreadyExistsException;
import com.projects.foodonlinecliapp.exceptions.CustomerNotFoundException;
import com.projects.foodonlinecliapp.model.Customer;

import java.util.List;

public interface CustomerService {
    public Customer save(Customer customer) throws CustomerAlreadyExistsException;

    public List<Customer> getAllCustomers();

    public Customer getCustomerById(String id);

    public Customer update(Customer customer);

    public void delete(String id) throws CustomerNotFoundException;

    public Customer validateCustomerLogin(String email, String password) throws CustomerNotFoundException;

    public void setCurrentLoggedInCustomer(Customer customer);

    public Customer getCurrentLoggedInCustomer();
}
