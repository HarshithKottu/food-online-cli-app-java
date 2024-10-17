package com.projects.foodonlinecliapp.repository;

import com.projects.foodonlinecliapp.model.Restaurant;
import com.projects.foodonlinecliapp.util.Factory;

import java.util.List;
import java.util.Optional;

public class RestaurantRepository {
    private final List<Restaurant> restaurants;

    public RestaurantRepository() {
        this.restaurants = Factory.getCsvReader().readRestaurantsFromCSV();
    }

    public List<Restaurant> getRestaurants() {
        return this.restaurants;
    }

    public Restaurant saveRestaurant(Restaurant restaurant) {
        this.restaurants.add(restaurant);
        return restaurant;
    }

    public Optional<Restaurant> getRestaurantById(String id) {
        return this.restaurants.stream().filter(restaurant -> restaurant.getId().equals(id)).findFirst();
    }
}
