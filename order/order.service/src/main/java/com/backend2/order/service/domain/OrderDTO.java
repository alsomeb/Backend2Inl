package com.backend2.order.service.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class OrderDTO {
    private Long id;
    private Long customerId;
    private LocalDate created;
    private LocalDate lastUpdated;
    private String jsonOrderContents;
}