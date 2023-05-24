package com.backend2.order.service.services.impl;

import com.backend2.order.service.domain.OrderDTO;
import com.backend2.order.service.domain.OrderEntity;
import com.backend2.order.service.exception.NoSuchCustomerException;
import com.backend2.order.service.exception.NoSuchOrderException;
import com.backend2.order.service.repositories.OrderRepository;
import com.backend2.order.service.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;


@Service
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
        ResponseEntity<Boolean> result = checkCustomerExistsById(id);

        // Om Orders finns på Customer ID return ORDER DTO Listan
        if(Boolean.TRUE.equals(result.getBody())) {
            return orderRepository.findOrderEntitiesByCustomerId(id).stream()
                    .map(this::toDTO)
                    .collect(Collectors.toList());
        }
            // Annars TOM LISTA om ej finns ORDERS på Customer ID
            return new ArrayList<>();

    }

    private ResponseEntity<Boolean> checkCustomerExistsById(long id) {
        String url = customerServiceRootURL.concat("customers/exists/").concat(String.valueOf(id));

        try {
            return restTemplate.exchange(url, HttpMethod.GET, null, Boolean.class);
        } catch (RestClientException e) {
            throw new NoSuchCustomerException("Cannot retrieve customer with id: " + id + " from customer-service");
        }
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