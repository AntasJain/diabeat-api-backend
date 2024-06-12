package com.antasjain.diabeat.dao;

import com.antasjain.diabeat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDAO extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);

    void deleteUserByUsername(String username);

    boolean existsByUsername(String username);

}
