package com.example.Book.Management.service;

import com.example.Book.Management.exception.BookNotFoundException;
import com.example.Book.Management.model.Book;
import com.example.Book.Management.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private BookRepository bookRepository;

    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book getBookById(Long id){
        return bookRepository.findById(id)
                .orElseThrow(()->new BookNotFoundException("Book not dound with id: "+id));
    }

    public Book updateBook(Long id,Book updated) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
        existingBook.setTitle(updated.getTitle());
        existingBook.setAuthor(updated.getAuthor());
        existingBook.setPrices(updated.getPrices());

        return bookRepository.save(existingBook);
    }

    public void deleteBook(Long id){
        Book book = bookRepository.findById(id)
                .orElseThrow(()->new BookNotFoundException("Book not found with id : "+id));
        bookRepository.delete(book);
    }

    public List<Book> serachByAuthor(String author){
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }


    public List<Book> serachByTitle(String title){
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }
}
