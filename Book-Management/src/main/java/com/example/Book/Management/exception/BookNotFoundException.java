package com.example.Book.Management.exception;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(Long id){
        super("❌ Book not Found with id: +id");
    }
}
