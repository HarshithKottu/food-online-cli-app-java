package com.projects.foodonlinecliapp.repository;

import com.projects.foodonlinecliapp.model.Customer;
import com.projects.foodonlinecliapp.util.Factory;

import java.util.List;
import java.util.Optional;

public class CustomerRepository {
    private final List<Customer> customerList;

    /*We create constructor to implement any functionality while creating Object
    ex: when we create CustomerRepository Object It should give list of customers automatically.*/
    public CustomerRepository() {
        this.customerList = Factory.getCsvReader().readCustomersFromCSV();
    }

    public List<Customer> getCustomerList() {
        return this.customerList;
    }

    public Customer save(Customer customer) {
        // This created to add a customer
        // Return type is Customer because after making change to list of customers..
        // we have to give update to the service layer that we have updated particular customer.
        this.customerList.add(customer);
        return customer;
    }

    public Optional<Customer> findCustomerByID(String id) {
        return this.customerList.stream().filter(customer -> customer.getId().equals(id)).findFirst();
    }

    public void deleteCustomer(Customer customer) {
        this.customerList.remove(customer);
    }

    public Optional<Customer> findByEmailAndPassword(String email, String password) {
        return this.customerList.stream().filter(customer -> (customer.getEmail().equals(email)) && customer.getPassword().equals(password)).findFirst();
    }

}
