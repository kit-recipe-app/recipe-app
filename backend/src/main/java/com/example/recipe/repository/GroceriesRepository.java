package com.example.recipe.repository;

import com.example.recipe.model.Groceries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroceriesRepository extends JpaRepository<Groceries, Long> {

}
