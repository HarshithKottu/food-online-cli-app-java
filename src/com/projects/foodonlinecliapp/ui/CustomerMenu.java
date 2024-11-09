package com.projects.foodonlinecliapp.ui;

import com.projects.foodonlinecliapp.controller.CustomerController;
import com.projects.foodonlinecliapp.exceptions.CustomerAlreadyExistsException;
import com.projects.foodonlinecliapp.exceptions.CustomerNotFoundException;
import com.projects.foodonlinecliapp.model.Customer;
import com.projects.foodonlinecliapp.util.Factory;

import java.util.List;
import java.util.Scanner;

public class CustomerMenu extends Menu {
    private final CustomerController customerController;

    public CustomerMenu() {
        this.customerController = Factory.getCustomerController();
    }

    public void displayCustomerMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("--------------------------");
            System.out.println("  WELCOME TO Customer Section");
            System.out.println("--------------------------");
            System.out.println();
            System.out.println("Please select the option !");
            System.out.println("--------------------------");
            System.out.println("1. Register (New Customer)");
            System.out.println("2. Login  (Existing Customer)");
            System.out.println("3. Search Customer");
            System.out.println("4. Display All Customers");
            System.out.println("5. Update Customer");
            System.out.println("6. Delete Customer");
            System.out.println("7. Exit");
            System.out.println("Please enter your choice (1-7)");

            int input = scanner.nextInt();
            switch (input) {
                case 1 -> displayRegisterMenu();
                case 2 -> displayLoginMenu();
                case 3 -> searchCustomer();
                case 4 -> displayAllCustomers();
                case 5 -> updateCustomer();
                case 6 -> deleteCustomer();
                case 7 -> displayMainMenu();
                default -> System.out.println("Invalid Input. Please enter the valid input from(1-7)");
            }
        }
    }

    private void displayRegisterMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please register entering the following details\n");
        System.out.println("Enter Id");
        String id = scanner.nextLine();
        System.out.println("Enter Name");
        String name = scanner.nextLine();
        System.out.println("Enter E-mail");
        String email = scanner.nextLine();
        System.out.println("Enter Password");
        String password = scanner.nextLine();
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName(name);
        customer.setEmail(email);
        customer.setPassword(password);
        /*CustomerRepository customerRepository = new CustomerRepository();
        CustomerServiceImpl customerService = new CustomerServiceImpl(customerRepository);
        CustomerController customerController = new CustomerController(customerService);*/
        //CustomerController customerController = Factory.getCustomerController();

        try {
            Customer savedCustomer = customerController.save(customer);
            System.out.println("Customer Registration Successful");
            System.out.println("Details: ");
            System.out.println("Id: " + savedCustomer.getId());
            System.out.println("Name: " + savedCustomer.getName());
            System.out.println("E-mail: " + savedCustomer.getEmail());
            System.out.println("Password: " + savedCustomer.getPassword());
            List<Customer> customerList = this.customerController.getCustomers();
        } catch (CustomerAlreadyExistsException e) {
            System.out.println(e.getMessage());
            displayCustomerMenu();
        }
    }

    private void displayLoginMenu() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Email: ");
            String email = scanner.nextLine();
            System.out.println("Password: ");
            String password = scanner.nextLine();
            Customer customer = this.customerController.validateCustomerLogin(email, password);
            this.customerController.setCurrentLoggedInCustomer(customer);
            System.out.println("Success Login");
        } catch (CustomerNotFoundException e) {
            System.out.println(e.getMessage());
            displayCustomerMenu();
        }

    }

    public void searchCustomer() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the Customer ID: ");
            String id = scanner.nextLine();
            Customer customer = this.customerController.getCustomerById(id);
            displayCustomerDetails(customer);
        } catch (CustomerNotFoundException e) {
            System.out.println(e.getMessage());
            displayCustomerMenu();
        }
    }

    public void displayAllCustomers() {
        List<Customer> customerList = this.customerController.getCustomers();
        String dashesLine = new String(new char[150]).replace('\0', '-');
        displayMenuHeader("Customers");
        System.out.printf("%-10s %-30s %-80s %-30s\n", "Id", "Name", "E-mail", "Password");
        System.out.println(dashesLine);
        customerList.forEach(customer -> {
            System.out.printf("%-10s %-30s %-80s %-30s\n", customer.getId(), customer.getName(), customer.getEmail(), "*".repeat(customer.getPassword().length()));
        });
    }

    public void updateCustomer() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the Customer details id, name, email, password");
            String id = scanner.nextLine();
            String name = scanner.nextLine();
            String email = scanner.nextLine();
            String password = scanner.nextLine();
            Customer customer = new Customer();
            customer.setId(id);
            customer.setName(name);
            customer.setEmail(email);
            customer.setPassword(password);
            this.customerController.getUpdate(customer);
        } catch (CustomerNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Internal Error Please try again");
            updateCustomer();
        }
    }

    public void deleteCustomer() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Customer ID to delete");
            String id = scanner.nextLine();
            this.customerController.delete(id);
        } catch (CustomerNotFoundException e) {
            System.out.println(e.getMessage());
            displayCustomerMenu();
        }
    }

    public void displayCustomerDetails(Customer customer) {
        displayMenuHeader("Customer Details");
        System.out.printf("%-10s %-30s %-80s %-30s\n", "Id", "Name", "E-mail", "Password");
        //printDashLine();
        System.out.printf("%-10s %-30s %-80s %-30s\n", customer.getId(), customer.getName(), customer.getEmail(), "*".repeat(customer.getPassword().length()));

    }

    public void displayMenuHeader(String menuHeader) {
        String dashesLine = new String(new char[110]).replace('\0', '-');
        System.out.println(dashesLine);
        String spaces = new String(new char[50]).replace('\0', ' ');
        System.out.printf("%-50s %-10s %-50s \n", spaces, menuHeader, spaces);
        System.out.println(dashesLine);
    }
}
