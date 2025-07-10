package com.example.LibraryManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.LibraryManagementSystem.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}

