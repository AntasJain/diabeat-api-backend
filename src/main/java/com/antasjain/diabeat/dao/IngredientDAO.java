package com.antasjain.diabeat.dao;

import com.antasjain.diabeat.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IngredientDAO extends JpaRepository<Ingredient,Integer> {
    Optional<Ingredient> findByName(String name);

    List<Ingredient> findAllByOrderByNameAsc();



}
