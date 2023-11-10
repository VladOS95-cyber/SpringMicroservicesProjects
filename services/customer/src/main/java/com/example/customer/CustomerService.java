package com.example.customer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService
{
    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    public void register(CustomerRegistrationRequest customerRequest)
    {
        Customer customer = Customer.builder()
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .email(customerRequest.email()).build();
        customerRepository.saveAndFlush(customer);
        FraudCheckResponse checkResponse = restTemplate.getForObject("http://FRAUD/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId());

        if(checkResponse.isFraudster())
        {
            throw new IllegalStateException("fraudster");
        }


    }
}
