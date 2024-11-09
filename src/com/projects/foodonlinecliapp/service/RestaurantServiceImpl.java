package com.projects.foodonlinecliapp.service;

import com.projects.foodonlinecliapp.exceptions.RestaurantAlreadyExistsException;
import com.projects.foodonlinecliapp.exceptions.RestaurantNotFoundException;
import com.projects.foodonlinecliapp.model.Restaurant;
import com.projects.foodonlinecliapp.repository.RestaurantRepository;

import java.util.List;
import java.util.Optional;

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
        Optional<Restaurant> restaurantById = this.restaurantRepository.getRestaurantById(restaurant.getId());
        if (restaurantById.isPresent())
            throw new RestaurantAlreadyExistsException("Restaurant already Exists with ID: " + restaurant.getId());
        return this.restaurantRepository.saveRestaurant(restaurant);
    }

    @Override
    public Restaurant getRestaurantById(String id) throws RestaurantNotFoundException {
        Optional<Restaurant> restaurantById = this.restaurantRepository.getRestaurantById(id);
        if (restaurantById.isEmpty())
            throw new RestaurantNotFoundException("Restaurant Not Found with given ID: " + id);
        return restaurantById.get();
    }

    @Override
    public void delete(String id) throws RestaurantNotFoundException {
        Optional<Restaurant> restaurantById = this.restaurantRepository.getRestaurantById(id);
        if (restaurantById.isEmpty())
            throw new RestaurantNotFoundException("Restaurant Not Found with given ID: " + id);
        this.restaurantRepository.delete(restaurantById.get());
    }

    @Override
    public Restaurant updateRestaurant(Restaurant restaurant) throws RestaurantNotFoundException {
        Optional<Restaurant> restaurantById = this.restaurantRepository.getRestaurantById(restaurant.getId());
        if (restaurantById.isEmpty())
            throw new RestaurantNotFoundException("Restaurant Not Found with given ID: " + restaurant.getId());
        return this.restaurantRepository.updateRestaurant(restaurant);

    }
}
