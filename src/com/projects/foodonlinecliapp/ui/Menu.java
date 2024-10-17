package com.projects.foodonlinecliapp.ui;

import com.projects.foodonlinecliapp.controller.CustomerController;
import com.projects.foodonlinecliapp.controller.DishController;
import com.projects.foodonlinecliapp.controller.RestaurantController;
import com.projects.foodonlinecliapp.exceptions.CustomerAlreadyExistsException;
import com.projects.foodonlinecliapp.model.Customer;
import com.projects.foodonlinecliapp.model.Dish;
import com.projects.foodonlinecliapp.model.Restaurant;
import com.projects.foodonlinecliapp.util.Factory;

import java.util.List;
import java.util.Scanner;

public class Menu {
    public void displayMainMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("--------------------------");
            System.out.println("  WELCOME TO FOODIE APP");
            System.out.println("--------------------------");
            System.out.println();
            System.out.println("Please select the option !");
            System.out.println("--------------------------");
            System.out.println("1. Register (New Customer)");
            System.out.println("2. Login  (Existing Customer)");
            System.out.println("3. View Restaurants");
            System.out.println("4. View DishMenu ");
            System.out.println("5. Place Order");
            System.out.println("6. View Orders");
            System.out.println("7. Exit");
            System.out.println("Please enter your choice (1-7)");

            int input = scanner.nextInt();
            switch (input) {
                case 1:
                    displayRegisteredMenu();
                    break;
                case 3:
                    displayRestaurantsList();
                    break;
                case 4:
                    //displayDishesList();
                    displayDishesListNew();
                    break;
                default:
                    System.out.println("Invalid Input. Please enter the valid input from(1-7)");
            }
        }
    }

    private void displayRegisteredMenu() {
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
        CustomerController customerController = Factory.getCustomerController();

        try {
            Customer savedCustomer = customerController.save(customer);
            System.out.println("Customer Registration Successful");
            System.out.println("Details: ");
            System.out.println("Id: " + customer.getId());
            System.out.println("Name: " + customer.getName());
            System.out.println("E-mail: " + customer.getEmail());
            System.out.println("Password: " + customer.getPassword());
        } catch (CustomerAlreadyExistsException e) {
            System.out.println(e.getMessage());
            displayMainMenu();
        }
    }

    private void displayDishesList() {
        DishController dishController = Factory.getDishController();
        //System.out.println(dishController.getDishesList());
        List<Dish> dishList = dishController.getDishesList();
        System.out.println("Id          Name            Description              Price");
        System.out.println("----------------------------------------------------------");
        dishList.forEach(dish -> {
            System.out.println(dish.getId() + "           " + dish.getName() + "           " + dish.getDescription() + "           " + dish.getPrice());
        });
        // The above code gives not a good alignment for the data. So.....

    }

    private void displayDishesListNew() {
        DishController dishController = Factory.getDishController();
        List<Dish> dishes = dishController.getDishesList();
        String dashesLine = new String(new char[150]).replace('\0', '-');
        displayMenuHeader("Menu Items");
        System.out.printf("%-10s %-30s %-80s %-10s\n", "Id", "Name", "Description", "Price");
        System.out.println(dashesLine);
        dishes.forEach(dish -> {
            System.out.printf("%-10s %-30s %-80s %-10s\n", dish.getId(), dish.getName(), dish.getDescription(), String.format("$%.2f", dish.getPrice()));
        });
    }

    public void displayRestaurantsList() {
        RestaurantController restaurantController = Factory.getRestaurantController();
        List<Restaurant> restaurants = restaurantController.getRestaurants();
        String dashesLine = new String(new char[110]).replace('\0', '-');
        displayMenuHeader("Restaurants");
        System.out.printf("%-10s %-30s %-40s %-10s\n", "Id", "Name", "Address", "Menu");
        System.out.println(dashesLine);
        restaurants.forEach(restaurant -> {
            System.out.printf("%-10s %-30s %-40s %-10s\n", restaurant.getId(), restaurant.getName(), restaurant.getAddress(), restaurant.getMenu());
        });
    }

    public void displayMenuHeader(String menuHeader) {
        String dashesLine = new String(new char[110]).replace('\0', '-');
        System.out.println(dashesLine);
        String spaces = new String(new char[50]).replace('\0', ' ');
        System.out.printf("%-50s %-10s %-50s \n", spaces, menuHeader, spaces);
        System.out.println(dashesLine);
    }

}
