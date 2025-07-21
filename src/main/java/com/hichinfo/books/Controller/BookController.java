package com.hichinfo.books.Controller;

import com.hichinfo.books.Entities.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    public final List<Book> books = new ArrayList<>();

    public BookController(){
        initializeBooks();
    }

    public void initializeBooks(){

        books.addAll(List.of(
                new Book("Title One", "Author One", "science"),
                new Book("Title Two", "Author Two", "science"),
                new Book("Title Three", "Author Three", "history"),
                new Book("Title Four", "Author Four", "math"),
                new Book("Title Five", "Author Five", "math"),
                new Book("Title Six", "Author Six", "math")
        ));
    }

    @GetMapping("/api/books")
    public List<Book> getBooks(){
        return books;
    }
}
