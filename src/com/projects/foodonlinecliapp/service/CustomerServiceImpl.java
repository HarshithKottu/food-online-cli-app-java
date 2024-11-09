package com.projects.foodonlinecliapp.service;

import com.projects.foodonlinecliapp.exceptions.CustomerAlreadyExistsException;
import com.projects.foodonlinecliapp.exceptions.CustomerNotFoundException;
import com.projects.foodonlinecliapp.model.Customer;
import com.projects.foodonlinecliapp.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

public class CustomerServiceImpl implements CustomerService {

    // instance variable. It uses more time so we created
    private final CustomerRepository customerRepository;
    private Customer currentLoggedInCustomer;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer save(Customer customer) throws CustomerAlreadyExistsException {
        // if customer already exists throw exception else Store/Save
        Optional<Customer> customerByID = this.customerRepository.findCustomerByID(customer.getId());
        if (customerByID.isPresent())
            throw new CustomerAlreadyExistsException("Customer already Exists with this id: " + customer.getId());

        return this.customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return this.customerRepository.getCustomerList();
    }

    @Override
    public Customer getCustomerById(String id) throws CustomerNotFoundException {
        return this.customerRepository.findCustomerByID(id).get();
    }

    @Override
    public Customer update(Customer customer) {
        Customer customer1 = this.customerRepository.findCustomerByID(customer.getId()).get();
        customer1.setName(customer.getName());
        customer1.setEmail(customer.getEmail());
        customer1.setPassword(customer.getPassword());
        return customer1;
    }

    @Override
    public void delete(String id) throws CustomerNotFoundException {
        Optional<Customer> customerById = this.customerRepository.findCustomerByID(id);
        if (customerById.isEmpty())
            throw new CustomerNotFoundException("Customer Not Found with Id: " + id);
        this.customerRepository.deleteCustomer(customerById.get());

    }

    @Override
    public Customer validateCustomerLogin(String email, String password) throws CustomerNotFoundException {
        Optional<Customer> customerById = this.customerRepository.findByEmailAndPassword(email, password);
        if (customerById.isEmpty())
            throw new CustomerNotFoundException("Invalid Email and Password");
        return customerById.get();
    }

    @Override
    public void setCurrentLoggedInCustomer(Customer customer) {
        this.currentLoggedInCustomer = customer;
    }

    @Override
    public Customer getCurrentLoggedInCustomer() {
        return this.currentLoggedInCustomer;
    }
}
