package com.kit.recipe.controller.api;



import com.kit.recipe.dto.AmountInformationDTO;
import com.kit.recipe.dto.IngredientDTO;
import com.kit.recipe.dto.RecipeDTO;
import com.kit.recipe.entities.AmountInformation;
import com.kit.recipe.entities.Ingredient;
import com.kit.recipe.entities.IngredientsWithAmount;
import com.kit.recipe.entities.Recipe;
import com.kit.recipe.repository.IngredientRepository;
import com.kit.recipe.repository.IngredientWithAmountRepository;
import com.kit.recipe.repository.RecipeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/api")
public class RecipeController {


    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;

    private final IngredientWithAmountRepository ingredientWithAmountRepository;

    public RecipeController(IngredientRepository ingredientRepository, RecipeRepository recipeRepository,  IngredientWithAmountRepository ingredientWithAmountRepository) {
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
        this.ingredientWithAmountRepository = ingredientWithAmountRepository;
    }


    @GetMapping("/ingredients")
    public ResponseEntity<List<Ingredient>> getIngredients() {
        return ResponseEntity.ok(ingredientRepository.findAll());
    }

    @PostMapping("/ingredients")
    public ResponseEntity<Ingredient> addIngredient(@RequestBody IngredientDTO ingredient) {
        if (ingredient.getName() == null || ingredient.getName().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Ingredient newIngredient = new Ingredient();
        newIngredient.setName(ingredient.getName());
        Optional<Ingredient> found =  ingredientRepository.findByNameContainsIgnoreCase(
                ingredient.getName()
            );
        if (found.isPresent()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(ingredientRepository.save(newIngredient));

    }

    @PostMapping("/recipes")
    public ResponseEntity<Recipe> addRecipe(@RequestBody RecipeDTO recipe) {
        Recipe newRecipe = new Recipe();
        newRecipe.setName(recipe.getName());
        return ResponseEntity.ok(recipeRepository.save(newRecipe));
    }



    @GetMapping("/recipes/{id}/ingredients")
    public ResponseEntity<List<IngredientsWithAmount>> getIngredientsForRecipe(@PathVariable String id) {
        Optional<Recipe> recipe = recipeRepository.findById(Long.parseLong(id));
        return recipe.map(value -> ResponseEntity.ok(value.getIngredients()))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @PostMapping("/recipes/{recipeId}/ingredients/{ingredientId}")
    public ResponseEntity<IngredientsWithAmount> addIngredientToRecipe(@PathVariable String recipeId, @RequestBody AmountInformationDTO amount, @PathVariable String ingredientId) {
        Optional<Recipe> recipe = recipeRepository.findById(Long.parseLong(recipeId));
        if (recipe.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Optional<Ingredient> found =  ingredientRepository.findById(Long.parseLong(ingredientId));
        if (found.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        IngredientsWithAmount ingredientsWithAmount = new IngredientsWithAmount();
        ingredientsWithAmount.setAmountInformation(new AmountInformation(amount.getAmount(), amount.getUnit()));
        ingredientsWithAmount.setIngredient(found.get());
        recipe.get().getIngredients().add(ingredientsWithAmount);

        ingredientWithAmountRepository.save(ingredientsWithAmount);
        recipeRepository.save(recipe.get());

        return ResponseEntity.ok(ingredientsWithAmount);
    }


}