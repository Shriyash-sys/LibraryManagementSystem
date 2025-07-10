package com.example.LibraryManagementSystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LibraryManagementSystem.model.Customer;
import com.example.LibraryManagementSystem.repository.CustomerRepository;
import com.example.LibraryManagementSystem.response.ApiResponse;

@Service

public class CustomerService {

    @Autowired
    private CustomerRepository customerRepo;

    public ApiResponse addCustomer(Customer customer) {
        Customer savecust = customerRepo.save(customer);
        return new ApiResponse("Customer added successfully", true, savecust);
    }

    public Iterable<Customer> getAllCustomers(){
        return customerRepo.findAll();
    }

    public ApiResponse updateCustomer(Long id, Customer customer) {
    Optional<Customer> existingCustomer = customerRepo.findById(id);

    if (existingCustomer.isPresent()) {
        Customer existing = existingCustomer.get();
        existing.setCustomerName(customer.getCustomerName());
        existing.setCustomerPhone(customer.getCustomerPhone());
        existing.setCustomerAddress(customer.getCustomerAddress());

        Customer updatedCustomer = customerRepo.save(existing);

        return new ApiResponse("Customer updated successfully", true, updatedCustomer);
    } else {
        return new ApiResponse("Customer not found", false, null);
    }
}


    public ApiResponse deleteCustomer(Long id) {
        Optional<Customer> customer = customerRepo.findById(id);

        if (customer.isPresent()) {
            Customer delCustomer = customer.get();
            customerRepo.deleteById(id);
            return new ApiResponse("Customer data deleted successfully", true, delCustomer);
        } else {
            return new ApiResponse("Customer not found", false, null);
        }
    }
}
