package com.antasjain.diabeat.service;

import com.antasjain.diabeat.dao.RecipeDAO;
import com.antasjain.diabeat.entity.Ingredient;
import com.antasjain.diabeat.entity.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeDAO recipeDAO;

    @Autowired
    public RecipeServiceImpl(RecipeDAO recipeDAO) {
        this.recipeDAO = recipeDAO;
    }

    @Override
    public List<Recipe> getAllRecipes(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Recipe> recipePage = recipeDAO.findAll(pageable);
        return recipePage.getContent();
    }

    @Override
    public Recipe getRecipeById(int id) {
        Optional<Recipe> optionalRecipe = recipeDAO.findById(id);
        return optionalRecipe.orElse(null);
    }

    @Override
    public Recipe saveRecipe(Recipe recipe) {
        return recipeDAO.save(recipe);
    }

    @Override
    public Recipe updateRecipe(int id, Recipe recipe) {
        if (recipeDAO.existsById(id)) {
            recipe.setId((long) id);
            return recipeDAO.save(recipe);
        }
        return null;
    }

    @Override
    public List<Recipe> getRecipesByIngredient(Ingredient ingredient) {
        return recipeDAO.findByIngredientsContaining(ingredient);
    }


    @Override
    public void deleteRecipe(int id) {
        recipeDAO.deleteById(id);
    }
    @Override
    public boolean existsById(int id) {
        return recipeDAO.existsById(id);
    }

}
