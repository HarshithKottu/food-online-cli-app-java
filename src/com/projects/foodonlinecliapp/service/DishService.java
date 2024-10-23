package com.projects.foodonlinecliapp.service;

import com.projects.foodonlinecliapp.exceptions.DishAlreadyExistsException;
import com.projects.foodonlinecliapp.exceptions.DishNotFoundException;
import com.projects.foodonlinecliapp.model.Dish;

import java.util.List;

public interface DishService {
    public List<Dish> getDishesList();

    public Dish save(Dish dish) throws DishAlreadyExistsException;

    public Dish getDishById(String id) throws DishNotFoundException;

    public Dish update(Dish dish);

    public void delete(String id) throws DishNotFoundException;
}
