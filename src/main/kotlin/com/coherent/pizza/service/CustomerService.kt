package com.coherent.pizza.service

import com.coherent.pizza.dto.CustomerDto
import com.coherent.pizza.model.Customer
import com.coherent.pizza.model.Topping
import com.coherent.pizza.repository.CustomerRepository
import org.springframework.stereotype.Service
import java.util.stream.Collectors
import javax.transaction.Transactional

@Service
class CustomerService(val repository: CustomerRepository) {

    @Transactional
    fun submit(dto: CustomerDto) {
        val customer = repository.findOneByEmail(dto.email)
        if (customer != null) {
            customer?.getToppings().clear()
            populateToppings(dto, customer)
            repository.save(customer)
        } else {
            val newCustomer = Customer(0, dto.email, mutableSetOf())
            populateToppings(dto, newCustomer)
            repository.save(newCustomer)
        }
    }

    fun getCustomerToppings(email: String): MutableList<String>? {
        val collect = repository.findByEmail(email)?.getToppings()?.stream()
                ?.map { it: Topping -> it.getToppingName() }
                ?.collect(Collectors.toList())

        return collect;
    }

    private fun populateToppings(dto: CustomerDto, customer: Customer?) {
        for (toppingName in dto.toppings) {
            val topping = Topping(toppingName)
            customer?.addTopping(topping)
        }
    }
}