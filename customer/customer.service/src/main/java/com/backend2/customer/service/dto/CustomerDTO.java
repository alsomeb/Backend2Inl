package com.backend2.customer.service.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CustomerDTO {
    private Long id;

    @NotEmpty(message = "First Name Is Mandatory")
    @Size(min = 3, message = "At least 3 Letters for first name")
    @Pattern(regexp="^[A-Öa-ö]*$", message = "Only Letters for first name")
    private String firstName;

    @NotEmpty(message = "Last name is mandatory.")
    @Size(min = 3, message = "At least 3 Letters for last name")
    @Pattern(regexp="^[A-Öa-ö]*$",message = "Only Letters for first name")
    private String lastName;

    @NotEmpty(message = "Social security number is mandatory.")
    @Size(min = 10, max = 12, message = "Social security number needs to be 10 or 12 digits")
    private String ssn;

    private LocalDate created;
    private LocalDate lastUpdated;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDTO customerDTO = (CustomerDTO) o;
        return Objects.equals(id, customerDTO.id) && Objects.equals(firstName, customerDTO.firstName) && Objects.equals(lastName, customerDTO.lastName) && Objects.equals(ssn, customerDTO.ssn) && Objects.equals(created, customerDTO.created) && Objects.equals(lastUpdated, customerDTO.lastUpdated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, ssn, created, lastUpdated);
    }
}
