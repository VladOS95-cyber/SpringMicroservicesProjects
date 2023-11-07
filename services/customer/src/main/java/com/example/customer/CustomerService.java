package com.example.customer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService
{
    private final CustomerRepository customerRepository;
    public void register(CustomerRegistrationRequest customerRequest)
    {
        Customer customer = Customer.builder()
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .email(customerRequest.email()).build();

        customerRepository.save(customer);
    }
}
