package com.backend2.order.service.services;

import com.backend2.order.service.domain.OrderDTO;
import com.backend2.order.service.domain.OrderEntity;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<OrderDTO> getAllOrders();


    OrderDTO getOrderById(Long id);


}