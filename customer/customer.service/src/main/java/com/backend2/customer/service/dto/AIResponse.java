package com.backend2.customer.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
    Service Response format
        {
            "role": "assistant",
            "content": "bla bla bla"
         }

 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AIResponse {

    private String role;
    private String content;
}
