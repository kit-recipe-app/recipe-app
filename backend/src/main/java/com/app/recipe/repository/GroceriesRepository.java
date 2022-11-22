package com.app.recipe.repository;

import com.app.recipe.model.Groceries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroceriesRepository extends JpaRepository<Groceries, Long> {

}
