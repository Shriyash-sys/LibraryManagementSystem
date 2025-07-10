package com.example.LibraryManagementSystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.repository.BookRepository;
import com.example.LibraryManagementSystem.response.ApiResponse;

@Service

public class BookService {

    @Autowired
    private BookRepository bookRepo;

    public ApiResponse addBook(Book book) {
        Book savebook = bookRepo.save(book);
        return new ApiResponse("Book added successfully", true, savebook);
    }

    public Iterable<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    public ApiResponse updateBook(Long id, Book book) {
        Optional<Book> existingBook = bookRepo.findById(id);

        if (existingBook.isPresent()) {
            Book exBook = existingBook.get();
            exBook.setBookName(book.getBookName());
            exBook.setBookNum(book.getBookNum());
            exBook.setBookPrice(book.getBookPrice());

            Book editbook = bookRepo.save(exBook);

            return new ApiResponse("Book updated successfully", true, editbook);
        } else {

            return new ApiResponse("Book not found", false, null);
        }
    }

    public ApiResponse deleteBook(Long id) {
        Optional<Book> book = bookRepo.findById(id);

        if (book.isPresent()) {
            Book delbook = book.get();
            bookRepo.deleteById(id);
            return new ApiResponse("Book deleted successfully", true, delbook);
        } else {
            return new ApiResponse("Book not found", false, null);
        }
    }
}