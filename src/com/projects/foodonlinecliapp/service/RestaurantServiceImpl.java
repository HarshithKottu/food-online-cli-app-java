package com.projects.foodonlinecliapp.service;

import com.projects.foodonlinecliapp.exceptions.RestaurantAlreadyExistsException;
import com.projects.foodonlinecliapp.exceptions.RestaurantNotFoundException;
import com.projects.foodonlinecliapp.model.Restaurant;
import com.projects.foodonlinecliapp.repository.RestaurantRepository;

import java.util.List;

public class RestaurantServiceImpl implements RestaurantService {

    private RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<Restaurant> getRestaurants() {
        return this.restaurantRepository.getRestaurants();
    }

    @Override
    public Restaurant saveRestaurant(Restaurant restaurant) throws RestaurantAlreadyExistsException {
        return null;
    }

    @Override
    public Restaurant getRestaurantById(String id) throws RestaurantNotFoundException {
        return null;
    }
}
