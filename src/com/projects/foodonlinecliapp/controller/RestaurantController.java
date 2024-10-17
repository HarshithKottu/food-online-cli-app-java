package com.projects.foodonlinecliapp.controller;

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
}
