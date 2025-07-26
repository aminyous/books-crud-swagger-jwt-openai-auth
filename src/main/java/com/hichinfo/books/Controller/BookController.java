package com.hichinfo.books.Controller;

import com.hichinfo.books.Entities.Book;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/api/books/forloop/{title}")
    public Book getBookByTitleForLoop(@PathVariable String title){

        for(Book book: books){
            if(book.getTitle().equalsIgnoreCase(title)){
                return book;
            }
        }
        return null;

    }

    @GetMapping("/api/books/{title}")
    public Book getBookByTitle(@PathVariable String title){

        return books.stream().filter(myBook -> myBook.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);

    }

    @GetMapping("/api/books/byCatForLoop")
    public List<Book> getBookByCategoryForLoop(@RequestParam(required = false) String category){

        List<Book> bookListCat = new ArrayList<>();

        if(category == null){
            return books;
        }

        for(Book book: books){
            if(book.getCategory().equalsIgnoreCase(category)){
                bookListCat.add(book);
            }
        }

        return bookListCat;
    }

    @GetMapping("/api/books/byCat")
    public List<Book> getBookByCategory(@RequestParam(required = false) String category){

        if(category == null){
            return books;
        }

        return books.stream()
                .filter(myBook -> myBook.getCategory().equalsIgnoreCase(category))
                .toList();

    }

    @GetMapping("/api/books/byCatTitle/{title}")
    public Book getBookByCategoryAndTitle(@PathVariable String title,
                                          @RequestParam(required = false) String category){


        if (category != null){
            List<Book> bookList = books.stream()
                    .filter(myBook -> myBook.getCategory().equalsIgnoreCase(category))
                    .toList();

            return bookList.stream()
                    .filter(myBook -> myBook.getTitle().equalsIgnoreCase(title))
                    .findFirst()
                    .orElse(null);
        }

        return null;
    }

    @PostMapping("/api/books")
    public void createBook(@RequestBody Book newBook){
        for(Book book: books){
            if(book.getTitle().equalsIgnoreCase(newBook.getTitle())){
                return;
            }
        }
        books.add(newBook);
    }


    @PostMapping("/api/booksStream")
    public void createBookStream(@RequestBody Book newBook){
        boolean isNewBook = books.stream()
                .noneMatch(book -> book.getTitle().equalsIgnoreCase(newBook.getTitle()));

        if(isNewBook){
            books.add(newBook);
        }
    }


}
