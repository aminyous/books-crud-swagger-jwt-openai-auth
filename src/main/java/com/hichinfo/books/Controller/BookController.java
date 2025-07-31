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


    @GetMapping("/forloop/{title}")
    public Book getBookByTitleForLoop(@PathVariable String title){

        for(Book book: books){
            if(book.getTitle().equalsIgnoreCase(title)){
                return book;
            }
        }
        return null;

    }

    @GetMapping("/{title}")
    public Book getBookByTitle(@PathVariable String title){

        return books.stream().filter(myBook -> myBook.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);

    }

    @GetMapping("/byCatForLoop")
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

    @GetMapping("/byCat")
    public List<Book> getBookByCategory(@RequestParam(required = false) String category){

        if(category == null){
            return books;
        }

        return books.stream()
                .filter(myBook -> myBook.getCategory().equalsIgnoreCase(category))
                .toList();

    }

    @GetMapping("/byCatTitle/{title}")
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

    @PostMapping()
    public void createBook(@RequestBody Book newBook){
        for(Book book: books){
            if(book.getTitle().equalsIgnoreCase(newBook.getTitle())){
                return;
            }
        }
        books.add(newBook);
    }


    @PostMapping("/Stream")
    public void createBookStream(@RequestBody Book newBook){
        boolean isNewBook = books.stream()
                .noneMatch(book -> book.getTitle().equalsIgnoreCase(newBook.getTitle()));

        if(isNewBook){
            books.add(newBook);
        }
    }

    @PutMapping("/{title}")
    public void updateBook(@PathVariable String title, @RequestBody Book updatedBook){
        for(int i = 0; i < books.size(); i++){
            if(books.get(i).getTitle().equalsIgnoreCase(title)){
                books.set(i, updatedBook);
                return;
            }
        }
    }

    @DeleteMapping("/{title}")
    public void deleteBook(@PathVariable String title){
        books.removeIf(book -> book.getTitle().equalsIgnoreCase(title));
    }


}
