package com.projects.foodonlinecliapp.controller;

import com.projects.foodonlinecliapp.model.Dish;
import com.projects.foodonlinecliapp.service.DishServiceImpl;

import java.util.List;

public class DishController {
    private DishServiceImpl dishService;

    public DishController(DishServiceImpl dishService) {
        this.dishService = dishService;
    }

    public List<Dish> getDishesList() {
        return this.dishService.getDishesList();
    }
}
