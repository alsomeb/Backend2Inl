package com.backend2.order.service.services.impl;



import com.backend2.order.service.domain.OrderDTO;
import com.backend2.order.service.domain.OrderEntity;
import com.backend2.order.service.repositories.OrderRepository;
import com.backend2.order.service.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;

    }


    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderEntity -> toDTO(orderEntity))
                .collect(Collectors.toList());
    }

    public Optional<OrderEntity> getOrderById(Long id) {
        return orderRepository.findById(id);
    }



    private OrderDTO toDTO(OrderEntity orderEntity) {
        return OrderDTO.builder()
                .id(orderEntity.getId())
                .lastUpdated(orderEntity.getLastUpdated())
                .created(orderEntity.getCreated())
                .build();
    }

}