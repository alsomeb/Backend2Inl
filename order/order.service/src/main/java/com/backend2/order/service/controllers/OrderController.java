package com.backend2.order.service.controllers;

import com.backend2.order.service.domain.OrderDTO;
import com.backend2.order.service.services.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("orders")
@Slf4j
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> allOrders = orderService.getAllOrders();
        log.info("Showing all, {}x orders in db", allOrders.size());

        return new ResponseEntity<>(allOrders, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderDTO> getOrderByID(@PathVariable Long id) {
        OrderDTO order = orderService.getOrderById(id);
        log.info("Orders on id: {}", id);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }


    // Pratar med Customer-service API
    @GetMapping("customers/{id}")
    public ResponseEntity<List<OrderDTO>> getOrdersByCustomerId(@PathVariable Long id) {
        List<OrderDTO> results = orderService.getOrdersByCustomerId(id);
        log.info("Orders by Customer ID: {}", results);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

}
