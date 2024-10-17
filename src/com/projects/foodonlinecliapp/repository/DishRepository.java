package com.projects.foodonlinecliapp.repository;

import com.projects.foodonlinecliapp.model.Customer;
import com.projects.foodonlinecliapp.model.Dish;
import com.projects.foodonlinecliapp.util.CsvReader;
import com.projects.foodonlinecliapp.util.Factory;

import java.util.List;
import java.util.Optional;

public class DishRepository {
    private List<Dish> dishList;

    public DishRepository() {
        this.dishList = Factory.getCsvReader().readDishesFromCSV();
    }

    public List<Dish> getDishList() {
        return this.dishList;
    }

    public Dish saveDish(Dish dish) {
        this.dishList.add(dish);
        return dish;
    }

    public Optional<Dish> getDishById(String id) {
        return this.dishList.stream().filter(dish -> dish.getId().equals(id)).findFirst();
    }
}
