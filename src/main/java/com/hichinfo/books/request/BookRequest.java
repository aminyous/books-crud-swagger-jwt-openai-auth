package com.hichinfo.books.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class BookRequest {
    @Size(min = 1, max = 30, message = "Title is between 1 and 30 characters")
    private String title;
    @Size(min = 1, max = 40, message = "Author is between 1 and 40 characters")
    private String author;
    @Size(min = 1, max = 30, message = "Category is between 1 and 30 characters")
    private String category;
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating cannot go past 5")
    private int rate;

    public BookRequest(String title, String author, String category, int rate) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.rate = rate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
