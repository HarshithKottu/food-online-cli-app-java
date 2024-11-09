package com.projects.foodonlinecliapp.controller;

import com.projects.foodonlinecliapp.exceptions.DishAlreadyExistsException;
import com.projects.foodonlinecliapp.exceptions.DishNotFoundException;
import com.projects.foodonlinecliapp.model.Dish;
import com.projects.foodonlinecliapp.service.DishServiceImpl;

import java.util.List;

public class DishController {
    private final DishServiceImpl dishService;

    public DishController(DishServiceImpl dishService) {
        this.dishService = dishService;
    }

    public List<Dish> getDishesList() {
        return this.dishService.getDishesList();
    }

    public Dish saveDish(Dish dish) throws DishAlreadyExistsException {
        return this.dishService.save(dish);
    }

    public Dish getDishById(String id) throws DishNotFoundException {
        return this.dishService.getDishById(id);
    }

    public Dish updateDish(Dish dishToBeUpdated) {
        return this.dishService.update(dishToBeUpdated);
    }

    public void deleteDish(String id) throws DishNotFoundException {
        this.dishService.delete(id);
    }
}
