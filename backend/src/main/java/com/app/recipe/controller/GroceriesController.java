package com.app.recipe.controller;


import com.app.recipe.model.Groceries;
import com.app.recipe.repository.GroceriesRepository;
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
        var testGrocery = new Groceries(3L, "Avocado", "Avocados sind toll");
        this.groceriesRepository.save(testGrocery);
        return ResponseEntity.ok(this.groceriesRepository.findAll());
    }

}
