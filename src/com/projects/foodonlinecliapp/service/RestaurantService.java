package com.projects.foodonlinecliapp.service;

import com.projects.foodonlinecliapp.exceptions.RestaurantAlreadyExistsException;
import com.projects.foodonlinecliapp.exceptions.RestaurantNotFoundException;
import com.projects.foodonlinecliapp.model.Restaurant;

import java.util.List;

public interface RestaurantService {
    public List<Restaurant> getRestaurants();

    public Restaurant saveRestaurant(Restaurant restaurant) throws RestaurantAlreadyExistsException;

    public Restaurant getRestaurantById(String id) throws RestaurantNotFoundException;

    public void delete(String id) throws RestaurantNotFoundException;

    public Restaurant updateRestaurant(Restaurant restaurant) throws RestaurantNotFoundException;
}
