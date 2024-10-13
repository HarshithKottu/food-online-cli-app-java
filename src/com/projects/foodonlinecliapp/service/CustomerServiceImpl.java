package com.projects.foodonlinecliapp.service;

import com.projects.foodonlinecliapp.exceptions.CustomerAlreadyExistsException;
import com.projects.foodonlinecliapp.model.Customer;
import com.projects.foodonlinecliapp.repository.CustomerRepository;

import java.util.Optional;

public class CustomerServiceImpl implements CustomerService {

    // instance variable. It uses more time so we created
    private CustomerRepository customerRepository;

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
}
