package com.hichinfo.books.request;

public class BookRequest {
    private String title;
    private String author;
    private String category;
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
