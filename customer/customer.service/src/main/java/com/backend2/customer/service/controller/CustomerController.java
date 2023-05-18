package com.backend2.customer.service.controller;

import com.backend2.customer.service.dto.CustomerDTO;
import com.backend2.customer.service.dto.DeleteResponse;
import com.backend2.customer.service.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("customers")
@Slf4j // Logging
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;




    //READ
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> listCustomers() {

        log.info("GET: All customers listed");

        return new ResponseEntity<>(customerService.listCustomers(), HttpStatus.OK);
    }

    // GET by ID
    @GetMapping("{id}")
    public ResponseEntity<EntityModel<CustomerDTO>> retrieveCustomer(@PathVariable final Long id) {
        final CustomerDTO foundCustomer = customerService.findCustomerById(id);


        // Hateoas links
        EntityModel<CustomerDTO> entityModel = EntityModel.of(foundCustomer,
                linkTo(methodOn(CustomerController.class).retrieveCustomer(foundCustomer.getId())).withSelfRel(),
                linkTo(methodOn(CustomerController.class).listCustomers()).withRel("all-customers"));

        log.info("GET: Customer with id: " + id);

        return new ResponseEntity<>(entityModel, HttpStatus.OK);
    }

    //UPDATE + CREATE
    @PutMapping("{id}")
    public ResponseEntity<EntityModel<CustomerDTO>> updateList(@Valid @RequestBody CustomerDTO customer, @PathVariable Long id) {

        customer.setId(id);

        final boolean exists = customerService.doesCustomerExist(customer);
        final CustomerDTO savedCustomer = customerService.save(customer);

        EntityModel<CustomerDTO> entityModel = EntityModel.of(savedCustomer, linkTo(methodOn(CustomerController.class).retrieveCustomer(savedCustomer.getId())).withSelfRel(),
                linkTo(methodOn(CustomerController.class).listCustomers()).withRel("all-lists"));

        if (exists) {
            log.info("UPDATED: customer: {}", savedCustomer);
            return new ResponseEntity<>(entityModel, HttpStatus.OK);
        }

        log.info("POST: customer: {}", savedCustomer);
        return new ResponseEntity<>(entityModel, HttpStatus.CREATED);
    }

    //DELETE
    @DeleteMapping("{id}")
    public ResponseEntity<DeleteResponse> deleteCustomerById(@PathVariable Long id) {
        DeleteResponse response = customerService.deleteCustomerById(id);

        log.info("DELETE RESPONSE: {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
