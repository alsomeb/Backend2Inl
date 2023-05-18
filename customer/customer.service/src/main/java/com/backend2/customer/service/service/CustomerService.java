package com.backend2.customer.service.service;

import com.backend2.customer.service.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> listCustomers();

    CustomerDTO findCustomerById(Long id);

    CustomerDTO create(CustomerDTO customer);

    CustomerDTO save(CustomerDTO customer);
    boolean deleteCustomerById(Long id);

    boolean doesCustomerExist(CustomerDTO customer);
}