package com.backend2.order.service.services.impl;



import com.backend2.order.service.domain.OrderDTO;
import com.backend2.order.service.repositories.OrderRepository;
import com.backend2.order.service.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;

    }


/*    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderEntity -> toDTO(orderEntity))
                .collect(Collectors.toList());
    }*/

    //TODO: SKRIV DENNA - Fellden
 /*   @Override
    public OrderDTO getOrderById(Long id) {

        //TODO: SKRIV
        return
    }*/



//TODO: SKRIV DENNA - Fellden
/*    private OrderDTO toDTO(OrderEntity orderEntity) {
        // All items related to order
        List<Item> itemsDTOList = orderEntity.getOrders()
                .stream()
                .map(orderItemEntity -> toItemDTO(orderItemEntity.getItemEntity()))
                .toList();

        return OrderDTO.builder()
                .id(orderEntity.getId())
                .lastUpdated(orderEntity.getLastUpdated())
                .created(orderEntity.getCreated())
                .items(itemsDTOList)
                .build();
    }*/

}