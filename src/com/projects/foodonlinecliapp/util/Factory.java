package com.projects.foodonlinecliapp.util;

import com.projects.foodonlinecliapp.controller.CustomerController;
import com.projects.foodonlinecliapp.controller.DishController;
import com.projects.foodonlinecliapp.controller.RestaurantController;
import com.projects.foodonlinecliapp.repository.CustomerRepository;
import com.projects.foodonlinecliapp.repository.DishRepository;
import com.projects.foodonlinecliapp.repository.RestaurantRepository;
import com.projects.foodonlinecliapp.service.CustomerServiceImpl;
import com.projects.foodonlinecliapp.service.DishServiceImpl;
import com.projects.foodonlinecliapp.service.RestaurantServiceImpl;

public class Factory {
    // static factory Methods
    public static CsvReader getCsvReader() {
        return new CsvReader();
    }

    public static final CustomerRepository getCustomerRepository() {
        return new CustomerRepository();
    }

    public static final CustomerServiceImpl getCustomerService() {
        return new CustomerServiceImpl(getCustomerRepository());
    }

    public static CustomerController getCustomerController() {
        return new CustomerController(getCustomerService());
    }

    public static DishRepository getDishRepository() {
        return new DishRepository();
    }

    public static DishServiceImpl getDishService() {
        return new DishServiceImpl(getDishRepository());
    }

    public static DishController getDishController() {
        return new DishController(getDishService());
    }

    public static RestaurantRepository getRestaurantRepository() {
        return new RestaurantRepository();
    }

    public static RestaurantServiceImpl getRestaurantService() {
        return new RestaurantServiceImpl(getRestaurantRepository());
    }

    public static RestaurantController getRestaurantController() {
        return new RestaurantController(getRestaurantService());
    }


}
