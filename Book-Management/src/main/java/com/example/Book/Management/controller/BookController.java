package com.example.Book.Management.controller;


import com.example.Book.Management.exception.BookNotFoundException;
import com.example.Book.Management.model.Book;
import com.example.Book.Management.repository.BookRepository;
import com.example.Book.Management.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

  @Autowired
  private BookService bookService;

   //create Book
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        Book savedBook = bookService.addBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    //Get All Book
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
       Book book = bookService.getBookById(id);
       return ResponseEntity.ok(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(
            @PathVariable Long id,
            @RequestBody Book bookDetails
    ) throws BookNotFoundException {
        Book updatedBook = bookService.updateBook(id, bookDetails);
        return ResponseEntity.ok(updatedBook);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return ResponseEntity.ok("Book Deleted Sucessfully..");
    }

    @GetMapping("search/author/{author}")
    public ResponseEntity<List<Book>> searchByAuthor(@PathVariable String author){
       List<Book> books = bookService.serachByAuthor(author);
       return ResponseEntity.ok(books);
    }

    @GetMapping("serach/auhtor/{title}")
    public List<Book> serachByTitle(@PathVariable String title){
       List<Book> books = bookService.serachByTitle(title);
       return ResponseEntity.ok(books).getBody();
    }

}
