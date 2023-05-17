package com.backend2.order.service.controllers;

import com.backend2.order.service.services.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("orders")
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    //TODO: FIXA
/*    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> allOrders = orderService.getAllOrders();
        log.info("Showing all, {}x orders in db", allOrders.size());

        return new ResponseEntity<>(allOrders, HttpStatus.OK);
    }*/





}