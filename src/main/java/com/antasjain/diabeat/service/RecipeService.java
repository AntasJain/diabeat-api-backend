package com.antasjain.diabeat.service;

import com.antasjain.diabeat.entity.Ingredient;
import com.antasjain.diabeat.entity.Recipe;

import java.util.List;

public interface RecipeService {



    List<Recipe> getAllRecipes(int page, int size);

    Recipe getRecipeById(int id);
        Recipe saveRecipe(Recipe recipe);
        Recipe updateRecipe(int id, Recipe recipe);
        void deleteRecipe(int id);
        boolean existsById(int id); // Add this method
        List<Recipe> getRecipesByIngredient(Ingredient ingredient); // Add this method


}
