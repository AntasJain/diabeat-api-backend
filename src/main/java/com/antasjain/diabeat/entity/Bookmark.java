package com.antasjain.diabeat.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bookmark")
@IdClass(BookmarkId.class)
public class Bookmark implements Serializable {

    @Id
    @Column(name = "username")
    private String username;

    @Id
    @Column(name = "recipe_id")
    private int recipeId;

    @ManyToOne
    @JoinColumn(name = "username", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "recipe_id", insertable = false, updatable = false)
    private Recipe recipe;

    // Constructors, getters, setters

    public Bookmark() {
    }

    public Bookmark(String username, int recipeId) {
        this.username = username;
        this.recipeId = recipeId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
