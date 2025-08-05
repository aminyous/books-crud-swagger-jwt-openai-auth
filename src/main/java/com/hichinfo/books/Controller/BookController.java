package com.hichinfo.books.Controller;

import com.hichinfo.books.Entities.Book;
import com.hichinfo.books.exception.BookErrorResponse;
import com.hichinfo.books.exception.BookNotFoundException;
import com.hichinfo.books.request.BookRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Books Rest API Endpoints", description = "Operations related to books")
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

//    @GetMapping()
//    public List<Book> getBooks(){
//        return books;
//    }
    @Operation(summary = "Get all books", description = "Retrieve a list of all books.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<Book> getBooks(@Parameter(description = "Optional query parameter")
                               @RequestParam(required = false) String category){
        if(category == null){
            return books;
        }

        return books.stream()
                .filter(book -> book.getCategory().equalsIgnoreCase(category))
                .toList();

    }


    @Operation(summary = "Get a book by Id", description = "Retrieve a specific book by Id.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Book getBookById(@Parameter(description = "Id of book to be retrieve")
                            @PathVariable @Min(value = 1) long id){

        return books.stream()
                .filter(myBook -> myBook.getId() == id)
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException("Book not found -  " + id));

    }


    @Operation(summary = "Create a new book ", description = "Add a new book to the list")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createBook(@Valid @RequestBody BookRequest bookRequest){

        long id = books.isEmpty() ?  1 : books.get(books.size() - 1).getId() + 1;

        books.add(convertToBook(id, bookRequest));
    }

    @Operation(summary = "Update a book", description = "Update a details of an existing book.")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public Book updateBook(@Parameter(description = "Id of book to update")
                           @Valid @PathVariable @Min(value = 1) long id,
                           @RequestBody BookRequest bookRequest){

        for(int i = 0; i < books.size(); i++){
            if(books.get(i).getId() == id){
                Book updatedBook = convertToBook(id, bookRequest);
                books.set(i, updatedBook);
                return updatedBook;
            }
        }

        throw new BookNotFoundException("Book not found - " + id);
    }
    @Operation(summary = "Delete a book", description = "Remove a book from the list.")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteBook(@Parameter(description = "id of the book to delete")
                           @PathVariable @Min(value = 1) long id){

        books.stream()
                .filter(myBook -> myBook.getId() == id)
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException("Book not found -  " + id));

        books.removeIf(book -> book.getId() == id);
    }


    private Book convertToBook(long id, BookRequest bookRequest){

        return new Book(
                id,
                bookRequest.getTitle(),
                bookRequest.getAuthor(),
                bookRequest.getCategory(),
                bookRequest.getRate()
        );

    }

    @ExceptionHandler
    public ResponseEntity<BookErrorResponse> handleException(BookNotFoundException exc){
        BookErrorResponse bookErrorResponse = new BookErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exc.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(bookErrorResponse, HttpStatus.NOT_FOUND);
    }


}
