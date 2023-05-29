package com.backend2.customer.service.service.impl;

import com.backend2.customer.service.Exception.NoSuchCustomerException;
import com.backend2.customer.service.dto.AIResponse;
import com.backend2.customer.service.dto.CustomerDTO;
import com.backend2.customer.service.dto.DeleteResponse;
import com.backend2.customer.service.entity.CustomerEntity;
import com.backend2.customer.service.repository.CustomerRepository;
import com.backend2.customer.service.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final RestTemplate restTemplate;

    @Value("${ai.service.url}")
    private String rootUrl;


    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, RestTemplate restTemplate) {
        this.customerRepository = customerRepository;
        this.restTemplate = restTemplate;
    }

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
            oldCustomerDTOMatch.setLastName(customerDTO.getLastName());
            oldCustomerDTOMatch.setFirstName(customerDTO.getFirstName());

            final CustomerEntity savedEntity = customerRepository.save(toEntity(oldCustomerDTOMatch));
            return toDTO(savedEntity);
        }

        var entityToSave = toEntity(customerDTO);
        entityToSave.setCreated(LocalDate.now());
        entityToSave.setLastUpdated(LocalDate.now());
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

    @Override
    public boolean existById(Long id) {
        return customerRepository.existsById(id);
    }

    @Override
    public AIResponse explainNameByCustomerId(Long id) {
        var matchCustomer = findCustomerById(id); // Om ej customer finns kommer den slå ifrån
        String nameToAsk = matchCustomer.getFirstName();
        String url = rootUrl + " " + nameToAsk;


        return restTemplate.getForObject(url, AIResponse.class);
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
