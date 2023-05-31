package com.coherent.pizza.controller

import com.coherent.pizza.dto.CustomerDto
import com.coherent.pizza.model.Topping
import com.coherent.pizza.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/customer")
class CustomerController(val service: CustomerService) {

    @PutMapping("/survey")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun submitSurvey(@RequestBody customerDto: CustomerDto) {
        val customer = service.submit(customerDto)
    }

    @GetMapping("/{email}")
    fun getCustomerToppings(@PathVariable("email") email: String): MutableSet<String>? {
        return service.getCustomerToppings(email)
    }

}