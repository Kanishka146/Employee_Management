package com.employeesystem.es.Services;

import com.employeesystem.es.Dto.SignUpRequest;
import com.employeesystem.es.Entities.Customer;
import com.employeesystem.es.Repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private final CustomerRepository customerRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    
    public AuthServiceImpl(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Customer createCustomer(SignUpRequest signUpRequest) {
        // Check if customer already exists
        if (customerRepository.existsByEmail(signUpRequest.getEmail())) {
            return null; // Or throw an exception if preferred
        }

        Customer customer = new Customer();
        BeanUtils.copyProperties(signUpRequest, customer);

        // Hash the password before saving
        String hashedPassword = passwordEncoder.encode(signUpRequest.getPassword());
        customer.setPassword(hashedPassword);
        
        Customer createdCustomer = customerRepository.save(customer);
        customer.setId(createdCustomer.getId());
        return customer;
    }
}
