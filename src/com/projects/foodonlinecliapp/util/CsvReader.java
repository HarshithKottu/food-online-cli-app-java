package com.projects.foodonlinecliapp.util;

import com.projects.foodonlinecliapp.model.Customer;
import com.projects.foodonlinecliapp.model.Dish;
import com.projects.foodonlinecliapp.model.Restaurant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {
    // Reads the data from CSV files and create a list of objects
    public List<Customer> readCustomersFromCSV() {
        String customersCsvFilePath = "C:\\repos\\food-online-cli-app-java\\data\\customers.csv";
        List<Customer> customerList = new ArrayList<>();
        // java io classes (FileReader, BufferReader) : You added task to learn this separately
        // try-with-resource : This is something which tries only when resource is found
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(customersCsvFilePath))) {
            String csvSplitBy = ",";
            br.readLine();
            while ((line = br.readLine()) != null) {
                //line = c001, john doe, john.doe@gmail.com, johndoe@1234
                String[] data = line.split(csvSplitBy);
                Customer customer = new Customer();
                customer.setId(data[0]);
                customer.setName(data[1]);
                customer.setEmail(data[2]);
                customer.setPassword(data[3]);
                customerList.add(customer);
            }
        } catch (IOException e) {
            System.out.println("File Not Found");
            e.printStackTrace();
        }
        return customerList;
    }

    public List<Dish> readDishesFromCSV() {
        String customersCsvFilePath = "C:\\repos\\food-online-cli-app-java\\data\\dishes.csv";
        List<Dish> dishes = new ArrayList<>();
        // java io classes (FileReader, BufferReader) : You added task to learn this separately
        // try-with-resource : This is something which tries only when resource is found
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(customersCsvFilePath))) {
            String csvSplitBy = ",";
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);
                Dish dish = new Dish();
                dish.setId(data[0]);
                dish.setName(data[1]);
                dish.setDescription(data[2]);
                dish.setPrice(Double.parseDouble(data[3]));
                dishes.add(dish);
            }
        } catch (IOException e) {
            System.out.println("File Not Found");
            e.printStackTrace();
        }
        return dishes;
    }

    public List<Restaurant> readRestaurantsFromCSV() {
        String customersCsvFilePath = "C:\\repos\\food-online-cli-app-java\\data\\restaurants.csv";
        List<Restaurant> restaurants = new ArrayList<>();
        // java io classes (FileReader, BufferReader) : You added task to learn this separately
        // try-with-resource : This is something which tries only when resource is found
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(customersCsvFilePath))) {
            String csvSplitBy = ",";
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);
                Restaurant restaurant = new Restaurant();
                restaurant.setId(data[0]);
                restaurant.setName(data[1]);
                restaurant.setAddress(data[2]);
                restaurant.setMenu(data[3]);
                restaurants.add(restaurant);
            }
        } catch (IOException e) {
            System.out.println("File Not Found");
            e.printStackTrace();
        }
        return restaurants;
    }
}
