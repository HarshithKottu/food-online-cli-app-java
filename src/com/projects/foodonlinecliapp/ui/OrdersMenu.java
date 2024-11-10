package com.projects.foodonlinecliapp.ui;

import com.projects.foodonlinecliapp.controller.OrderController;
import com.projects.foodonlinecliapp.exceptions.DishNotFoundException;
import com.projects.foodonlinecliapp.exceptions.OrderAlreadyExistsException;
import com.projects.foodonlinecliapp.exceptions.OrderNotFoundException;
import com.projects.foodonlinecliapp.exceptions.RestaurantNotFoundException;
import com.projects.foodonlinecliapp.model.Customer;
import com.projects.foodonlinecliapp.model.Dish;
import com.projects.foodonlinecliapp.model.Order;
import com.projects.foodonlinecliapp.model.Restaurant;
import com.projects.foodonlinecliapp.service.CustomerService;
import com.projects.foodonlinecliapp.service.DishService;
import com.projects.foodonlinecliapp.service.RestaurantService;
import com.projects.foodonlinecliapp.util.Factory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class OrdersMenu extends Menu {
    private final OrderController orderController;

    public OrdersMenu() {
        this.orderController = Factory.getOrderController();
    }

    public void displayOrdersMenu() {
        try {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("--------------------------");
                System.out.println("  WELCOME TO Orders Section");
                System.out.println("--------------------------");
                System.out.println();
                System.out.println("Please select the option !");
                System.out.println("--------------------------");
                System.out.println("1. Create Order");
                System.out.println("2. Search Order");
                System.out.println("3. View All Orders");
                System.out.println("4. Exit");
                System.out.println("Please enter your choice (1-4)");

                int input = scanner.nextInt();
                switch (input) {
                    case 1 -> createOrder();
                    case 2 -> searchOrder();
                    case 3 -> viewOrders();
                    case 4 -> {
                        System.out.println("Thank you , See you again !");
                        super.displayMainMenu();
                    }
                    default -> System.out.println("Invalid Input. Please enter the valid input from(1-4)");

                }
            }
        } catch (Exception e) {
            System.out.println("Some internal error occurred. Please try again !");
            displayMainMenu();
        }
    }

    private void createOrder() {
        Customer loggedInCustomer = null;
        Restaurant restaurant = null;
        List<Dish> dishList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(System.in);
            CustomerService customerService = Factory.getCustomerService();
            RestaurantService restaurantService = Factory.getRestaurantService();
            DishService dishService = Factory.getDishService();
            loggedInCustomer = customerService.getCurrentLoggedInCustomer();
            while (loggedInCustomer == null) {
                System.out.println("Login to Place an Order");
                new CustomerMenu().displayCustomerMenu();
                loggedInCustomer = customerService.getCurrentLoggedInCustomer();
            }
            System.out.println("Order ID:");
            String id = scanner.nextLine();
            while (restaurant == null) {
                new RestaurantsMenu().displayRestaurantsMenu();
                printDashLine();
                System.out.println("Select the Restaurant from where you want to Order Ex: R003");
                String restaurantId = scanner.nextLine();
                restaurant = restaurantService.getRestaurantById(id);
            }
            char addMoreItems = 'Y';
            while (addMoreItems == 'Y') {
                new RestaurantsMenu().displayMenuItems(restaurant.getId());
                printDashLine();
                System.out.println("Enter the Dish Id (Ex : D001 )");
                String dishId = scanner.nextLine();
                Dish selectedDish = dishService.getDishById(dishId);
                dishList.add(selectedDish);
                System.out.println("One Dish is added successfully : " + selectedDish.getName());
                System.out.println("Do you want to add more dishes (Y/N)");
                addMoreItems = scanner.nextLine().charAt(0);
            }
            double orderPrice = calculateOrderTotalPrice(dishList);
            LocalDate orderDate = LocalDate.now();


            Order order = new Order();
            order.setId(id)
                    .setCustomer(loggedInCustomer)
                    .setRestaurant(restaurant)
                    .setDishes(dishList)
                    .setTotalPrice(orderPrice)
                    .setOrderDate(orderDate);

            Order placedOrder = orderController.saveOrder(order);
            if (placedOrder != null)
                System.out.println("Order Placed Successfully with the following details");

            displayOrderDetails(placedOrder);

        } catch (RestaurantNotFoundException | DishNotFoundException | OrderAlreadyExistsException e) {
            throw new RuntimeException(e);
        }
    }

    private void searchOrder() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the Order ID");
            String id = scanner.nextLine();
            Order order = orderController.getOrderById(id);
            displayOrderDetails(order);
        } catch (OrderNotFoundException e) {
            System.out.println(e.getMessage());
            displayMainMenu();
        }
    }

    private void viewOrders() {
        List<Order> orderList = orderController.getOrderList();
        orderList.forEach(order -> {
            String dishNames = order.getDishes().stream().map(Dish::getName).collect(Collectors.joining(","));
            System.out.printf("%-10s %-20s %-30s %-60s %-20s %-10s\n\n", order.getId(), order.getCustomer().getName(), order.getRestaurant().getName(), dishNames, order.getOrderDate(), order.getTotalPrice());
        });
        System.out.println("\n");
    }

    private void displayOrderDetails(Order order) {
        String dishNames = order.getDishes().stream().map(Dish::getName).collect(Collectors.joining(","));
        displayMenuHeader("Order Details");
        System.out.printf("%-10s %-20s %-30s %-60s %-20s %-10s\n", "Id", "Customer Name", "Restaurant Name", "Items", "Order Date", "Price");
        printDashLine();
        System.out.printf("%-10s %-20s %-30s %-60s %-20s %-10s\n\n", order.getId(), order.getCustomer().getName(), order.getRestaurant().getName(), dishNames, order.getOrderDate(), String.format("$%.2f", order.getTotalPrice()));


    }

    private double calculateOrderTotalPrice(List<Dish> dishList) {
        return dishList.stream().mapToDouble(Dish::getPrice).sum();
    }
}
