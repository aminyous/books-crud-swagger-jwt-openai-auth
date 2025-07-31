package com.hichinfo.books.Entities;

public class Book {
    private long id;
    private String title;
    private String author;
    private String category;
    private int rate;


    public Book(long id, String title, String author, String category, int rate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.rate = rate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
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
}
