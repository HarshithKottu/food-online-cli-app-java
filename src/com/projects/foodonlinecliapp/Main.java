package com.projects.foodonlinecliapp;

import com.projects.foodonlinecliapp.repository.CustomerRepository;
import com.projects.foodonlinecliapp.repository.DishRepository;
import com.projects.foodonlinecliapp.util.CsvReader;

public class Main {
    public static void main(String[] args) {
        CsvReader csvReader = new CsvReader();
        System.out.println(csvReader.readCustomersFromCSV());
        System.out.println(csvReader.readDishesFromCSV());
        System.out.println(csvReader.readRestaurantsFromCSV());

        CustomerRepository customerRepository = new CustomerRepository();
        System.out.println(customerRepository.getCustomerList());

        DishRepository dishRepository = new DishRepository();
        System.out.println(dishRepository.getDishList());
    }
}
