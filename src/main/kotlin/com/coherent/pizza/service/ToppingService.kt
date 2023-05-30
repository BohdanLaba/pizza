package com.coherent.pizza.service

import com.coherent.pizza.dto.AggregationResponseDto
import com.coherent.pizza.repository.ToppingRepository
import org.springframework.stereotype.Service

@Service
class ToppingService(val repository: ToppingRepository) {

    fun aggregateAllToppings(): List<AggregationResponseDto> = repository.aggregateToppings()
}