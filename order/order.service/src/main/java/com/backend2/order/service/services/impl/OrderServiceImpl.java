package com.backend2.order.service.services.impl;

import com.backend2.order.service.domain.OrderDTO;
import com.backend2.order.service.domain.OrderEntity;
import com.backend2.order.service.exception.CustomerServiceConnectionException;
import com.backend2.order.service.exception.NoSuchOrderException;
import com.backend2.order.service.repositories.OrderRepository;
import com.backend2.order.service.services.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;

import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;


@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    private final RestTemplate restTemplate;

    @Value("${customer-service.url}")
    private String customerServiceRootURL;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, RestTemplate restTemplate) {
        this.orderRepository = orderRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderEntity -> toDTO(orderEntity))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        var match = orderRepository.findById(id)
                .orElseThrow( () -> new NoSuchOrderException("No order with this id: " + id + " found"));
        return toDTO(match);
    }

    @Override
    public List<OrderDTO> getOrdersByCustomerId(Long id) {
        log.info("Check Customer Exists with Customer API");
        boolean customerExist = checkCustomerExistsById(id);

        // Om Orders finns på Customer ID return ORDER DTO Listan
        if(customerExist) {
            return orderRepository.findOrderEntitiesByCustomerId(id).stream()
                    .map(this::toDTO)
                    .collect(Collectors.toList());
        }
            // Annars TOM LISTA om ej finns ORDERS på Customer ID
            return new ArrayList<>();

    }

    @Override
    public List<OrderDTO> recoveryGetOrdersByCustomerId(Long id) {
        log.info("Performing Recovery, Connection Refused With Customer API");
        throw new CustomerServiceConnectionException("Cannot connect to Customer API");
    }


    /*
        exchange() method PARAMS:

        url – the URL

        method – the HTTP method (GET, POST, etc)

        requestEntity – the entity (headers and/or body) to write to the request may be null)

        responseType – the type to convert the response to, or Void.class for no body

        uriVariables – the variables to expand in the template

        Spring Retry: https://www.baeldung.com/spring-retry
     */
    private boolean checkCustomerExistsById(long id) {
        String url = customerServiceRootURL.concat("customers/exists/").concat(String.valueOf(id));
        ResponseEntity<Boolean> resultResponse =  restTemplate.exchange(url, HttpMethod.GET, null, Boolean.class);
        return resultResponse.getBody();
    }



    private OrderDTO toDTO(OrderEntity orderEntity) {
        return OrderDTO.builder()
                .id(orderEntity.getId())
                .customerId(orderEntity.getCustomerId())
                .lastUpdated(orderEntity.getLastUpdated())
                .created(orderEntity.getCreated())
                .build();
    }

}