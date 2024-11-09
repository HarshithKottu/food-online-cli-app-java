package com.projects.foodonlinecliapp.controller;

import com.projects.foodonlinecliapp.exceptions.RestaurantAlreadyExistsException;
import com.projects.foodonlinecliapp.exceptions.RestaurantNotFoundException;
import com.projects.foodonlinecliapp.model.Restaurant;
import com.projects.foodonlinecliapp.service.RestaurantServiceImpl;

import java.util.List;

public class RestaurantController {
    private final RestaurantServiceImpl restaurantService;

    public RestaurantController(RestaurantServiceImpl restaurantService) {
        this.restaurantService = restaurantService;
    }

    public List<Restaurant> getRestaurants() {
        return this.restaurantService.getRestaurants();
    }

    public Restaurant saveRestaurant(Restaurant restaurant) throws RestaurantAlreadyExistsException {
        return this.restaurantService.saveRestaurant(restaurant);
    }

    public Restaurant getRestaurantById(String id) throws RestaurantNotFoundException {
        return this.restaurantService.getRestaurantById(id);
    }

    public Restaurant updateRestaurant(Restaurant restaurant) throws RestaurantNotFoundException {
        return this.restaurantService.updateRestaurant(restaurant);
    }

    public void deleteRestaurant(String id) throws RestaurantNotFoundException {
        this.restaurantService.delete(id);
    }
}
