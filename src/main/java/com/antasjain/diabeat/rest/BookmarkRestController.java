package com.antasjain.diabeat.rest;

import com.antasjain.diabeat.exceptions.ResourceNotFoundException;
import com.antasjain.diabeat.entity.Bookmark;
import com.antasjain.diabeat.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookmarks")
public class BookmarkRestController {

    private final BookmarkService bookmarkService;

    @Autowired
    public BookmarkRestController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @PostMapping
    public Bookmark addBookmark(@RequestBody Bookmark bookmark) {
        return bookmarkService.saveBookmark(bookmark);
    }

    @DeleteMapping
    public void deleteBookmark(@RequestParam String username, @RequestParam int recipeId) {
        if (!bookmarkService.existsByUsernameAndRecipeId(username, recipeId)) {
            throw new ResourceNotFoundException("Bookmark not found with username " + username + " and recipeId " + recipeId);
        }
        bookmarkService.deleteBookmark(username, recipeId);
    }

    @GetMapping
    public List<Bookmark> getAllBookmarks(
            String username){
        return bookmarkService.getBookmarksByUsername(username);
    }
}
