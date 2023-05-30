package com.coherent.pizza.dto

data class CustomerDto(
        val email: String,
        val toppings: Set<String>
)
