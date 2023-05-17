package com.backend2.item.service.domains;

import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Item {

    private Long id;
    private String name;
    private Long price;
    private Long balance;
    private LocalDate created;
    private LocalDate lastUpdated;
    private String URI;

}
