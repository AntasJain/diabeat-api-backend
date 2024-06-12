package com.antasjain.diabeat.entity;

import java.io.Serializable;
import java.util.Objects;

public class BookmarkId implements Serializable {

    private String username;
    private int recipeId;

    // Default constructor, equals, and hashCode

    public BookmarkId() {
    }

    public BookmarkId(String username, int recipeId) {
        this.username = username;
        this.recipeId = recipeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookmarkId that = (BookmarkId) o;
        return recipeId == that.recipeId &&
                Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, recipeId);
    }
}
