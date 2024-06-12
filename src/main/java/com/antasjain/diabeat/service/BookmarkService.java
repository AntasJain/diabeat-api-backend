package com.antasjain.diabeat.service;

import com.antasjain.diabeat.entity.Bookmark;

import java.util.List;

public interface BookmarkService {
    List<Bookmark> getBookmarksByUsername(String username);
    Bookmark saveBookmark(Bookmark bookmark);
    void deleteBookmark(String username, int recipeId);

    boolean existsByUsernameAndRecipeId(String username, int recipeId);
}
