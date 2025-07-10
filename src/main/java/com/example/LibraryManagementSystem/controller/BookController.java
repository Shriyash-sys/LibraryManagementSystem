package com.example.LibraryManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.response.ApiResponse;
import com.example.LibraryManagementSystem.service.BookService;

@RestController
@RequestMapping("api")
public class BookController {

    @Autowired

    private BookService bookService;

    @PostMapping("books")
    public ResponseEntity<ApiResponse> addBook(@RequestBody Book book) {
        ApiResponse response = bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("books")
    public Iterable<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PutMapping("books/{id}")
    public ResponseEntity<ApiResponse> updateBook(@PathVariable Long id, @RequestBody Book book) {
        ApiResponse response = bookService.updateBook(id, book);

        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("books/{id}")
    public ApiResponse deleteBook(@PathVariable Long id) {
        return bookService.deleteBook(id);
    }
}
