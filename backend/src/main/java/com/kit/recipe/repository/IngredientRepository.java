package com.kit.recipe.repository;

import com.kit.recipe.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Optional<Ingredient> findByNameContainsIgnoreCase(@NonNull String name);
}

