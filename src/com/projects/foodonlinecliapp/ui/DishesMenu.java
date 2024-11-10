package com.projects.foodonlinecliapp.ui;

import com.projects.foodonlinecliapp.controller.DishController;
import com.projects.foodonlinecliapp.exceptions.DishAlreadyExistsException;
import com.projects.foodonlinecliapp.exceptions.DishNotFoundException;
import com.projects.foodonlinecliapp.model.Dish;
import com.projects.foodonlinecliapp.util.Factory;

import java.util.List;
import java.util.Scanner;

public class DishesMenu extends Menu {
    private final DishController dishController;

    public DishesMenu() {
        this.dishController = Factory.getDishController();
    }

    public void displayDishMenu() {
        try {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("--------------------------");
                System.out.println("  WELCOME TO Dish Section");
                System.out.println("--------------------------");
                System.out.println();
                System.out.println("Please select the option !");
                System.out.println("--------------------------");
                System.out.println("1. Add Dish");
                System.out.println("2. display All Dishes");
                System.out.println("3. Search Dish");
                System.out.println("4. Update Dish");
                System.out.println("5. Delete Customer");
                System.out.println("6. Exit");
                System.out.println("Please enter your choice (1-6)");

                int input = scanner.nextInt();
                switch (input) {
                    case 1 -> addDish();
                    case 2 -> displayDishes();
                    case 3 -> searchDish();
                    case 4 -> updateDish();
                    case 5 -> deleteDish();
                    case 6 -> {
                        System.out.println("Thank you , See you again !");
                        super.displayMainMenu();
                    }
                    default -> System.out.println("Invalid Input. Please enter the valid input from(1-6)");

                }
            }
        } catch (Exception e) {
            System.out.println("Some internal error occurred. Please try again !");
            displayMainMenu();
        }
    }

    public void addDish() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the Dish");
            System.out.println("Enter Id");
            String id = scanner.nextLine();
            System.out.println("Enter Name");
            String name = scanner.nextLine();
            System.out.println("Enter Description");
            String description = scanner.nextLine();
            System.out.println("Enter Price");
            double price = scanner.nextDouble();
            Dish dish = new Dish();
            dish.setId(id)
                    .setName(name)
                    .setDescription(description)
                    .setPrice(price);

            Dish savedDish = this.dishController.saveDish(dish);
            displayDish(savedDish);
        } catch (DishAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    public void displayDishes() {
        List<Dish> dishes = this.dishController.getDishesList();
        String dashesLine = new String(new char[150]).replace('\0', '-');
        displayMenuHeader("Dish Items");
        System.out.printf("%-10s %-30s %-80s %-10s\n", "Id", "Name", "Description", "Price");
        System.out.println(dashesLine);
        dishes.forEach(dish -> {
            System.out.printf("%-10s %-30s %-80s %-10s\n", dish.getId(), dish.getName(), dish.getDescription(), String.format("$%.2f", dish.getPrice()));
        });
    }

    public void searchDish() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the ID");
            String id = scanner.nextLine();
            Dish dish = this.dishController.getDishById(id);
            displayDish(dish);
        } catch (DishNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateDish() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the Updated Dish Details");
            String id = scanner.nextLine();
            String name = scanner.nextLine();
            String description = scanner.nextLine();
            double price = scanner.nextDouble();
            Dish dish = new Dish();
            dish.setId(id)
                    .setName(name)
                    .setDescription(description)
                    .setPrice(price);
            Dish updatedDish = dishController.updateDish(dish);
            System.out.println("Dish Updated Successfully");
            displayDish(updatedDish);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteDish() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter the following details to delete the Dish\n");
            System.out.println("Enter Id");
            String id = scanner.nextLine();
            dishController.deleteDish(id);
            System.out.println("Dish Deleted Successfully");
        } catch (DishNotFoundException e) {
            System.out.println(e.getMessage());
            //displayMenu();
        }
    }

    public void displayDish(Dish dish) {
        displayMenuHeader("Dish Details");
        System.out.printf("%-10s %-30s %-80s %-10s\n", "Id", "Name", "Description", "Price");
        printDashLine();
        System.out.printf("%-10s %-30s %-80s %-10s\n", dish.getId(), dish.getName(), dish.getDescription(), String.format("$%.2f", dish.getPrice()));

    }

    public void displayMenuHeader(String menuHeader) {
        printDashLine();
        String spaces = new String(new char[70]).replace('\0', ' ');
        System.out.printf("%-70s %-10s %-70s \n", spaces, menuHeader, spaces);
        printDashLine();
    }

    public void printDashLine() {
        String dashesLine = new String(new char[150]).replace('\0', '-');
        System.out.println(dashesLine);
    }

}
