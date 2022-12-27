package com.kit.recipe.repository;

import com.kit.recipe.entities.IngredientsWithAmount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientWithAmountRepository extends JpaRepository<IngredientsWithAmount, Long> {

}