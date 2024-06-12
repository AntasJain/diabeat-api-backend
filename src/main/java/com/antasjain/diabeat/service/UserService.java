package com.antasjain.diabeat.service;

import com.antasjain.diabeat.dao.RecipeDAO;
import com.antasjain.diabeat.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByUsername(String username);

    void deleteByUsername(String username);
    User save(User user);

    boolean existsByUsername(String username);
}
