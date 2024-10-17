package com.projects.foodonlinecliapp.service;

import com.projects.foodonlinecliapp.exceptions.CustomerAlreadyExistsException;
import com.projects.foodonlinecliapp.exceptions.DishAlreadyExistsException;
import com.projects.foodonlinecliapp.exceptions.DishNotFoundException;
import com.projects.foodonlinecliapp.model.Customer;
import com.projects.foodonlinecliapp.model.Dish;
import com.projects.foodonlinecliapp.repository.DishRepository;

import java.util.List;
import java.util.Optional;

public class DishServiceImpl implements DishService {

    private DishRepository dishRepository;

    public DishServiceImpl(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Override
    public List<Dish> getDishesList() {
        return this.dishRepository.getDishList();
    }

    @Override
    public Dish save(Dish dish) throws DishAlreadyExistsException {
        Optional<Dish> dishById = this.dishRepository.getDishById(dish.getId());
        if (dishById.isPresent())
            throw new DishAlreadyExistsException("Dish already Exists with this id: " + dish.getId());

        return this.dishRepository.saveDish(dish);
    }

    @Override
    public Dish getDishById(String id) throws DishNotFoundException {
        Optional<Dish> dishById = this.dishRepository.getDishById(id);
        if (dishById.isEmpty())
            throw new DishNotFoundException("No dish with given ID: " + id);
        return dishById.get();
    }
}
