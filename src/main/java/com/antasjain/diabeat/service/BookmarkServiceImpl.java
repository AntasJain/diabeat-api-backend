package com.antasjain.diabeat.service;

import com.antasjain.diabeat.dao.BookmarkDAO;
import com.antasjain.diabeat.entity.Bookmark;
import com.antasjain.diabeat.entity.BookmarkId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookmarkServiceImpl implements BookmarkService {

    private final BookmarkDAO bookmarkDAO;

    @Autowired
    public BookmarkServiceImpl(BookmarkDAO bookmarkDAO) {
        this.bookmarkDAO = bookmarkDAO;
    }

    @Override
    public List<Bookmark> getBookmarksByUsername(String username) {
        return bookmarkDAO.findByUsername(username);
    }

    @Override
    public Bookmark saveBookmark(Bookmark bookmark) {
        return bookmarkDAO.save(bookmark);
    }

    @Override
    public void deleteBookmark(String username, int recipeId) {
        BookmarkId bookmarkId = new BookmarkId(username, recipeId);
        bookmarkDAO.deleteById(bookmarkId);
    }

    @Override
    public boolean existsByUsernameAndRecipeId(String username, int recipeId) {
        return bookmarkDAO.existsBookmarkByUsernameAndRecipeId(username,recipeId);
    }
}
