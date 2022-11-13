package com.example.recipe.controller;


import com.example.recipe.model.Groceries;
import com.example.recipe.repository.GroceriesRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/groceries")
public class GroceriesController {
    private final GroceriesRepository groceriesRepository;


    public GroceriesController(GroceriesRepository groceriesRepository) {
        this.groceriesRepository = groceriesRepository;
    }


    @GetMapping
    public ResponseEntity<List<Groceries>> getAllGroceries() {
        return ResponseEntity.ok(this.groceriesRepository.findAll());
    }

}
