package com.example.LibraryManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.LibraryManagementSystem.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
