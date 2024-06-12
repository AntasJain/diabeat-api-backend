package com.antasjain.diabeat.rest;

import com.antasjain.diabeat.entity.Ingredient;
import com.antasjain.diabeat.entity.Recipe;
import com.antasjain.diabeat.exceptions.ResourceNotFoundException;
import com.antasjain.diabeat.service.IngredientService;
import com.antasjain.diabeat.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recipes")
public class RecipeRestController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    @Autowired
    public RecipeRestController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping("/{id}")
    public Recipe getRecipeById(@PathVariable int id) {
        Optional<Recipe> recipeOptional = Optional.ofNullable(recipeService.getRecipeById(id));
        if (recipeOptional.isPresent()) {
            return recipeOptional.get();
        } else {
            throw new ResourceNotFoundException("Recipe not found with id " + id);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Recipe updateRecipe(@PathVariable int id, @RequestBody Recipe recipe) {
        if (recipeService.existsById(id)) {
            return recipeService.saveRecipe(recipe);
        } else {
            throw new ResourceNotFoundException("Recipe not found with id " + id);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteRecipe(@PathVariable int id) {
        if (!recipeService.existsById(id)) {
            throw new ResourceNotFoundException("Recipe not found with id " + id);
        }
        recipeService.deleteRecipe(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Recipe> getAllRecipes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return recipeService.getAllRecipes(page, size);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        List<Ingredient> ingredients = recipe.getIngredients();
        List<Ingredient> savedIngredients = new ArrayList<>();

        for (Ingredient ingredient : ingredients) {
            String ingredientName = ingredient.getName();
            Optional<Ingredient> ingredientOptional = ingredientService.getIngredientByName(ingredientName);
            Ingredient savedIngredient = ingredientOptional.orElseGet(() -> {
                Ingredient newIngredient = new Ingredient();
                newIngredient.setName(ingredientName);
                return ingredientService.saveIngredient(newIngredient);
            });
            savedIngredients.add(savedIngredient);
        }

        recipe.setIngredients(savedIngredients);
        return recipeService.saveRecipe(recipe);
    }
}
