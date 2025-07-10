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

import com.example.LibraryManagementSystem.model.Customer;
import com.example.LibraryManagementSystem.response.ApiResponse;
import com.example.LibraryManagementSystem.service.CustomerService;

@RestController
@RequestMapping("api")
public class CustomerController {

    @Autowired

    private CustomerService customerService;

    @PostMapping("customers")
    public ResponseEntity<ApiResponse> addCustomer(@RequestBody Customer customer) {
        ApiResponse response = customerService.addCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("customers")
    public Iterable<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PutMapping("customers/{id}")
    public ResponseEntity<ApiResponse> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        ApiResponse response = customerService.updateCustomer(id, customer);

        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("customers/{id}")
    public ApiResponse deleteCustomer(@PathVariable Long id) {
        return customerService.deleteCustomer(id);
    }
}
