package com.backend2.customer.service.service.impl;

import com.backend2.customer.service.dto.CustomerDTO;
import com.backend2.customer.service.entity.CustomerEntity;
import com.backend2.customer.service.repository.CustomerRepository;
import com.backend2.customer.service.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public List<CustomerDTO> listCustomers() {
        return null;
    }

    @Override
    public CustomerDTO findCustomerById(Long id) {
        return null;
    }

    @Override
    public CustomerDTO create(CustomerDTO customer) {
        return null;
    }

    @Override
    public CustomerDTO save(CustomerDTO customer) {
        return null;
    }

    @Override
    public boolean deleteCustomerById(Long id) {
        return false;
    }

    @Override
    public boolean doesCustomerExist(CustomerDTO customer) {
        return false;
    }


    // HELPER METHODS
    private CustomerEntity toEntity(CustomerDTO customerDTO) {
        return CustomerEntity.builder()
                .id(customerDTO.getId())
                .firstName(customerDTO.getFirstName())
                .lastName(customerDTO.getLastName())
                .ssn(customerDTO.getSsn())
                .created(customerDTO.getCreated())
                .lastUpdated(customerDTO.getLastUpdated())
                .build();
    }

    private CustomerDTO toDTO(CustomerEntity customerEntity) {
        // Todo
        return CustomerDTO.builder().build();
    }
}
