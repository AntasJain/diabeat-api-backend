package com.antasjain.diabeat.dao;

import com.antasjain.diabeat.entity.Ingredient;
import com.antasjain.diabeat.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecipeDAO extends JpaRepository<Recipe,Integer> {
     Recipe save(Recipe recipe);
     List<Recipe> findByIngredientsContaining(Ingredient ingredient);
}
