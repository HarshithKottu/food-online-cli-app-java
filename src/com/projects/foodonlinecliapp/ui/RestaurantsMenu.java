package com.projects.foodonlinecliapp.ui;

import com.projects.foodonlinecliapp.controller.RestaurantController;
import com.projects.foodonlinecliapp.exceptions.DishNotFoundException;
import com.projects.foodonlinecliapp.exceptions.RestaurantAlreadyExistsException;
import com.projects.foodonlinecliapp.exceptions.RestaurantNotFoundException;
import com.projects.foodonlinecliapp.model.Dish;
import com.projects.foodonlinecliapp.model.Restaurant;
import com.projects.foodonlinecliapp.service.RestaurantService;
import com.projects.foodonlinecliapp.util.Factory;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class RestaurantsMenu extends Menu {
    private final RestaurantController restaurantController;

    public RestaurantsMenu() {
        this.restaurantController = Factory.getRestaurantController();
    }

    public void displayRestaurantsMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("--------------------------");
            System.out.println("  WELCOME TO Restaurant Section");
            System.out.println("--------------------------");
            System.out.println();
            System.out.println("Please select the option !");
            System.out.println("--------------------------");

            System.out.println("1. Add New Restaurant)");
            System.out.println("2. View all Restaurants");
            System.out.println("3. Search Restaurant");
            System.out.println("4. Update Restaurant");
            System.out.println("5. Delete Restaurant");
            System.out.println("6. Exit");
            System.out.println("Please enter your choice (1-6)");

            int input = scanner.nextInt();
            switch (input) {
                case 1 -> newRestaurantForm();
                case 2 -> displayAllRestaurants();
                case 3 -> searchRestaurant();
                case 4 -> updateCustomer();
                case 5 -> deleteCustomer();
                case 6 -> displayMainMenu();
                default -> System.out.println("Invalid Input. Please enter the valid input from(1-7)");
            }
        }
    }

    public void newRestaurantForm() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Restaurants details");
            System.out.println("Enter Restaurant ID: ");
            String id = scanner.nextLine();
            System.out.println("Name");
            String name = scanner.nextLine();
            System.out.println("Address");
            String address = scanner.nextLine();
            System.out.println("Enter Dishes for menu in this format -> D001:D009");
            String menu = scanner.nextLine();
            Restaurant restaurant = new Restaurant();
            restaurant.setId(id);
            restaurant.setName(name);
            restaurant.setAddress(address);
            restaurant.setMenu(Arrays.asList(menu.split(":")));
            restaurantController.saveRestaurant(restaurant);
        } catch (RestaurantAlreadyExistsException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Internal error");
            newRestaurantForm();
        }
    }

    public void displayAllRestaurants() {
        List<Restaurant> restaurants = this.restaurantController.getRestaurants();
        restaurants.forEach(restaurant -> {
            System.out.printf("%-10s %-30s %-80s %-30s\n", restaurant.getId(), restaurant.getName(), restaurant.getAddress(), String.join(":", restaurant.getMenu()));
        });
    }

    public void searchRestaurant() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the Restaurant ID");
            String id = scanner.nextLine();
            Restaurant restaurant = restaurantController.getRestaurantById(id);
            System.out.printf("%-10s %-30s %-80s %-30s\n", restaurant.getId(), restaurant.getName(), restaurant.getAddress(), String.join(":", restaurant.getMenu()));
        } catch (RestaurantNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateCustomer() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the following changes: ");
            Restaurant restaurant = new Restaurant();
            String id = scanner.nextLine();
            String name = scanner.nextLine();
            String address = scanner.nextLine();
            System.out.println("Enter the  menu in following format: 'C011:C012'");
            String menu = scanner.nextLine();
            restaurant.setId(id)
                    .setName(name)
                    .setAddress(address)
                    .setMenu(menu);
            Restaurant updatedRestaurant = this.restaurantController.updateRestaurant(restaurant);
            System.out.printf("%-10s %-30s %-80s %-30s\n", updatedRestaurant.getId(), updatedRestaurant.getName(), updatedRestaurant.getAddress(), String.join(":", updatedRestaurant.getMenu()));
        } catch (RestaurantNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteCustomer() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("enter the Restaurant ID which you want to delete");
            String id = scanner.nextLine();
            this.restaurantController.deleteRestaurant(id);
            System.out.println("Restaurant deleted successfully");
        } catch (RestaurantNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void displayMenuItems(String restaurantId) throws RestaurantNotFoundException, DishNotFoundException {
        displayMenuHeader("Dishes Menu Details");
        System.out.printf("%-10s %-30s %-80s %-10s\n", "Id", "Name", "Description", "Price");
        printDashLine();
        RestaurantService restaurantService = Factory.getRestaurantService();
        List<Dish> dishItems = restaurantService.getDishItems(restaurantId);
        for (Dish dish : dishItems) {
            System.out.printf("%-10s %-30s %-80s %-10s\n", dish.getId(), dish.getName(), dish.getDescription(), String.format("$%.2f", dish.getPrice()));
        }
    }
}
