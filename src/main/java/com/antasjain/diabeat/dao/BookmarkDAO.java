package com.antasjain.diabeat.dao;

import com.antasjain.diabeat.entity.Bookmark;
import com.antasjain.diabeat.entity.BookmarkId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkDAO extends JpaRepository<Bookmark, BookmarkId> {
    List<Bookmark> findByUsername(String username);
    boolean existsBookmarkByUsernameAndRecipeId(String username, int recipeId);
}
