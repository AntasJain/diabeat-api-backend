package com.antasjain.diabeat.service;

import com.antasjain.diabeat.entity.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientService{
    List<Ingredient> getAllIngredients();
    Optional<Ingredient> getIngredientById(int id);
    Ingredient saveIngredient(Ingredient ingredient);
    void deleteIngredient(int id);

    Optional<Ingredient> getIngredientByName(String name);

}
