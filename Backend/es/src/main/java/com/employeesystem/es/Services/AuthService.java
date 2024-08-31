package com.employeesystem.es.Services;

import com.employeesystem.es.Dto.SignUpRequest;
import com.employeesystem.es.Entities.Customer;

public interface AuthService {
    Customer createCustomer(SignUpRequest signUpRequest);
}
