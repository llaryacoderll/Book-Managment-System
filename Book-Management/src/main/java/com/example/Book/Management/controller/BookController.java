package com.example.Book.Management.controller;


import com.example.Book.Management.model.Book;
import com.example.Book.Management.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

   private final BookRepository repo;

   public BookController(BookRepository repo){
       this.repo=repo;
   }

   //create Book
    @PostMapping
    public Book createBook(@RequestBody Book book){
        return repo.save(book);
    }

    //Get All Book
    @GetMapping
    public List<Book> getAllBooks(){
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Book> getBookById(@PathVariable Long id){
        return repo.findById(id);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id,@RequestBody Book upadatedBook){
        return repo.findById(id).map(book->{
            book.setTitle(upadatedBook.getTitle());
            book.setAuthor(upadatedBook.getAuthor());
            book.setPrices(upadatedBook.getPrices());
            return repo.save(book);
        })
                .orElseThrow(()->new RuntimeException("Book not found with id " +id));
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id){
        repo.deleteById(id);
        return "book delted sucessfully...";
    }

    @GetMapping("serach/author/{author}")
    public List<Book> searchByAuthor(@PathVariable String author){
       return repo.findByAuthorContainingIgnoreCase(author);
    }

    @GetMapping("serach/auhtor/{title}")
    public List<Book> serachByTitle(@PathVariable String title){
       return repo.findByTitleContainingIgnoreCase(title);
    }

}
