package com.example.LibraryManagementSystem.repository;

import java.util.List;
import com.example.LibraryManagementSystem.model.Order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerId(Long customerId);
    List<Order> findByBookId(Long bookId); 

}
