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

import com.example.LibraryManagementSystem.request.OrderRequest;
import com.example.LibraryManagementSystem.response.ApiResponse;
import com.example.LibraryManagementSystem.service.OrderService;

@RestController
@RequestMapping("api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/make")
    public ResponseEntity<ApiResponse> makeOrder(@RequestBody OrderRequest orderRequest) {
        ApiResponse response = orderService.makeOrder(orderRequest.getCustomerId(), orderRequest.getBookId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<ApiResponse> getCustomerOrders(@PathVariable Long id) {
        ApiResponse response = orderService.getOrderByCustomer(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<ApiResponse> updateOrder(
            @PathVariable Long orderId,
            @RequestBody OrderRequest updateRequest) {

        ApiResponse response = orderService.updateOrder(orderId, updateRequest.getBookId(),
                updateRequest.getCustomerId());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<ApiResponse> deleteOrder(@PathVariable Long orderId) {
        ApiResponse response = orderService.deleteOrder(orderId);
        return ResponseEntity.ok(response);
    }

}
