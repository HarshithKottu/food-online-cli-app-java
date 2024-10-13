package com.projects.foodonlinecliapp.repository;

import com.projects.foodonlinecliapp.model.Customer;
import com.projects.foodonlinecliapp.model.Dish;
import com.projects.foodonlinecliapp.util.CsvReader;

import java.util.List;

public class DishRepository {
    private List<Dish> dishList;

    public DishRepository() {
        CsvReader csvReader = new CsvReader();
        this.dishList = csvReader.readDishesFromCSV();
    }

    public List<Dish> getDishList() {
        return this.dishList;
    }
}
