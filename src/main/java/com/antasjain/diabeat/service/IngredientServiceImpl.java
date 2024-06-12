package com.antasjain.diabeat.service;

import com.antasjain.diabeat.dao.IngredientDAO;
import com.antasjain.diabeat.entity.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientDAO ingredientDAO;

    @Autowired
    public IngredientServiceImpl(IngredientDAO ingredientDAO) {
        this.ingredientDAO = ingredientDAO;
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return ingredientDAO.findAll();
    }

    @Override
    public Optional<Ingredient> getIngredientById(int id) {
        return ingredientDAO.findById(id);
    }

    @Override
    public Ingredient saveIngredient(Ingredient ingredient) {
        return ingredientDAO.save(ingredient);
    }

    @Override
    public void deleteIngredient(int id) {
        ingredientDAO.deleteById(id);
    }

    @Override
    public Optional<Ingredient> getIngredientByName(String name) {
        return ingredientDAO.findByName(name);
    }
}
