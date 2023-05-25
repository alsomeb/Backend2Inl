package com.backend2.order.service.services;

import com.backend2.order.service.domain.OrderDTO;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

import java.util.List;

public interface OrderService {

    List<OrderDTO> getAllOrders();


    OrderDTO getOrderById(Long id);

    // Return Type + Param must be same on @Retryable and @Recover
    @Retryable
    List<OrderDTO> getOrdersByCustomerId(Long id);

    @Recover
    List<OrderDTO> recoveryGetOrdersByCustomerId(Long id);

}