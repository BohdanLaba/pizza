package com.coherent.pizza.controller

import com.coherent.pizza.dto.AggregationResponseDto
import com.coherent.pizza.service.ToppingService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("api/v1/topping")
class ToppingController(val service: ToppingService) {

    @GetMapping()
    fun aggregateAllToppings(): List<AggregationResponseDto> {
        return service.aggregateAllToppings()
    }
}