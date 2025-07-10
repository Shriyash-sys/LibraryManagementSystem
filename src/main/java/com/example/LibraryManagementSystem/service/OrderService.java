package com.example.LibraryManagementSystem.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.model.Customer;
import com.example.LibraryManagementSystem.model.Order;
import com.example.LibraryManagementSystem.repository.BookRepository;
import com.example.LibraryManagementSystem.repository.CustomerRepository;
import com.example.LibraryManagementSystem.repository.OrderRepository;
import com.example.LibraryManagementSystem.response.ApiResponse;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private CustomerRepository customerRepo;

    public ApiResponse makeOrder(Long customerId, Long bookId) {
        try {
            Customer customer = customerRepo.findById(customerId)
                    .orElseThrow(() -> new RuntimeException("Customer not found"));

            Book book = bookRepo.findById(bookId)
                    .orElseThrow(() -> new RuntimeException("Book not found"));

            Order order = new Order();
            order.setCustomer(customer);
            order.setBook(book);
            order.setOrderDate(LocalDate.now());

            Order saveorders = orderRepo.save(order);

            return new ApiResponse("Order placed successfully", true, saveorders); // ✅ include data
        } catch (RuntimeException ex) {
            return new ApiResponse(ex.getMessage(), false, null);
        } catch (Exception ex) {
            return new ApiResponse("Failed to place order", false, null);
        }
    }

    public ApiResponse getOrderByCustomer(Long customerId) {
        List<Order> orders = orderRepo.findByCustomerId(customerId);

        if (orders.isEmpty()) {
            return new ApiResponse("No orders found for this customer", false, null);
        }

        return new ApiResponse("Orders retrieved successfully", true, orders); // ✅ include data
    }

    public ApiResponse updateOrder(Long orderId, Long newBookId, Long newCustomerId) {
        Optional<Order> optionalOrder = orderRepo.findById(orderId);

        if (optionalOrder.isEmpty()) {
            return new ApiResponse("Order not found", false, null);
        }

        Order order = optionalOrder.get();

        // Find and set new customer if provided
        if (newCustomerId != null) {
            Customer customer = customerRepo.findById(newCustomerId)
                    .orElseThrow(() -> new RuntimeException("Customer not found"));
            order.setCustomer(customer);
        }

        // Find and set new book if provided
        if (newBookId != null) {
            Book book = bookRepo.findById(newBookId)
                    .orElseThrow(() -> new RuntimeException("Book not found"));
            order.setBook(book);
        }

        Order updatedOrder = orderRepo.save(order);

        return new ApiResponse("Order updated successfully", true, updatedOrder);
    }

    public ApiResponse deleteOrder(Long orderId) {
        Optional<Order> optionalOrder = orderRepo.findById(orderId);

        if (optionalOrder.isEmpty()) {
            return new ApiResponse("Order not found", false, null);
        }
        orderRepo.deleteById(orderId);
        return new ApiResponse("Order deleted successfully", true, null);
    }

}
