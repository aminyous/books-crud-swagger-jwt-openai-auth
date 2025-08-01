package com.hichinfo.books.Controller;

import com.hichinfo.books.Entities.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/books")
@RestController
public class BookController {

    public final List<Book> books = new ArrayList<>();

    public BookController(){
        initializeBooks();
    }

    public void initializeBooks(){

        books.addAll(List.of(
                new Book(1, "Computer Science Pro", "Chad Darby", "Computer Science", 5),
                new Book(2, "Java Spring Master", "Eric Roby", "Computer Science", 5),
                new Book(3, "Why 1+1 Rocks", "Adil A.", "Math", 5),
                new Book(4, "How Bears Hibernate", "Bob B.", "Science", 2),
                new Book(5, "A Pirate's Treasure", "Curt C.", "History", 3),
                new Book(6, "Why 2+2 is Better", "Dad D.", "Math", 1)
        ));
    }

    @GetMapping()
    public List<Book> getBooks(){
        return books;
    }




    @GetMapping("/{id}")
    public Book getBookById(@PathVariable long id){

        return books.stream()
                .filter(myBook -> myBook.getId() == id)
                .findFirst()
                .orElse(null);

    }



    @PostMapping
    public void createBook(@RequestBody Book newBook){
        boolean isNewBook = books.stream()
                .noneMatch(book -> book.getTitle().equalsIgnoreCase(newBook.getTitle()));

        if(isNewBook){
            books.add(newBook);
        }
    }

    @PutMapping("/{id}")
    public void updateBook(@PathVariable long id, @RequestBody Book updatedBook){
        for(int i = 0; i < books.size(); i++){
            if(books.get(i).getId() == id){
                books.set(i, updatedBook);
                return;
            }
        }
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable long id){
        books.removeIf(book -> book.getId() == id);
    }


}
