package com.backend2.customer.service.service.impl;

import com.backend2.customer.service.Exception.NoSuchCustomerException;
import com.backend2.customer.service.dto.CustomerDTO;
import com.backend2.customer.service.dto.DeleteResponse;
import com.backend2.customer.service.entity.CustomerEntity;
import com.backend2.customer.service.repository.CustomerRepository;
import com.backend2.customer.service.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public List<CustomerDTO> listCustomers() {
        final List<CustomerEntity> found = customerRepository.findAll();
        return found.stream()
                .map(customerEntity -> toDTO(customerEntity))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO findCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(customerEntity -> toDTO(customerEntity))
                .orElseThrow(() -> new NoSuchCustomerException("No Customer with id: " + id));
    }

    @Override
    public CustomerDTO create(CustomerDTO customerDTO) {
        customerDTO.setCreated(LocalDate.now());
        customerDTO.setLastUpdated(LocalDate.now());

        var savedEntity = customerRepository.save(toEntity(customerDTO));
        return toDTO(savedEntity);
    }

    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {
        boolean exists = doesCustomerExist(customerDTO);

        if(exists) {
            var oldCustomerDTOMatch = findCustomerById(customerDTO.getId());
            oldCustomerDTOMatch.setLastUpdated(LocalDate.now());
            oldCustomerDTOMatch.setSsn(customerDTO.getSsn());
            oldCustomerDTOMatch.setCreated(customerDTO.getCreated());
            oldCustomerDTOMatch.setLastName(customerDTO.getLastName());
            oldCustomerDTOMatch.setFirstName(customerDTO.getFirstName());

            final CustomerEntity savedEntity = customerRepository.save(toEntity(oldCustomerDTOMatch));
            return toDTO(savedEntity);
        }

        var entityToSave = toEntity(customerDTO);
        var savedCustomerEntity = customerRepository.save(entityToSave);

        return toDTO(savedCustomerEntity);
    }

    @Override
    public DeleteResponse deleteCustomerById(Long id) {
        Optional<CustomerEntity> match = customerRepository.findById(id);
        if(match.isPresent()) {
            customerRepository.deleteById(id);
            return new DeleteResponse(true);
        }
        return new DeleteResponse(false);
    }

    @Override
    public boolean doesCustomerExist(CustomerDTO customerDTO) {
        return customerRepository.existsById(customerDTO.getId());
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
        return CustomerDTO.builder()
                .id(customerEntity.getId())
                .firstName(customerEntity.getFirstName())
                .lastName(customerEntity.getLastName())
                .ssn(customerEntity.getSsn())
                .created(customerEntity.getCreated())
                .lastUpdated(customerEntity.getLastUpdated())
                .build();
    }
}
