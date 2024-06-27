package com.antasjain.diabeat.rest;

import com.antasjain.diabeat.exceptions.ResourceNotFoundException;
import com.antasjain.diabeat.entity.Ingredient;
import com.antasjain.diabeat.entity.Recipe;
import com.antasjain.diabeat.service.IngredientService;
import com.antasjain.diabeat.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientRestController {

    private final IngredientService ingredientService;
    private final RecipeService recipeService;

    @Autowired
    public IngredientRestController(IngredientService ingredientService, RecipeService recipeService) {
        this.ingredientService = ingredientService;
        this.recipeService = recipeService;
    }

    @GetMapping("/all")
    public List<Ingredient> getAllIngredients(){
        return ingredientService.getAllIngredients();
    }

    @GetMapping("/{name}/recipes")
    public List<Recipe> getRecipesByIngredient(@PathVariable String name) {
        Ingredient ingredient = ingredientService.getIngredientByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Ingredient not found with name " + name));
        return recipeService.getRecipesByIngredient(ingredient);
    }
}
