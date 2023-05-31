package com.coherent.pizza.service

import com.coherent.pizza.dto.CustomerDto
import com.coherent.pizza.model.Customer
import com.coherent.pizza.model.Topping
import com.coherent.pizza.repository.CustomerRepository
import com.coherent.pizza.repository.ToppingRepository
import org.springframework.stereotype.Service
import java.util.stream.Collectors
import javax.transaction.Transactional

@Service
class CustomerService(val customerRepository: CustomerRepository, val toppingRepository: ToppingRepository) {

    /**
     * In order to keep Topping table clearer, I`ve used ManyToMany relationship
     * & retrieve existing toppings from db.
     *
     */
    @Transactional
    fun submit(dto: CustomerDto) {
        val customer = customerRepository.findByEmail(dto.email)
        if (customer != null) {
            customer.getToppings().clear()
            populateToppings(dto, customer)
            customerRepository.save(customer)
        } else {
            val newCustomer = Customer(0, dto.email, mutableSetOf())
            populateToppings(dto, newCustomer)
            customerRepository.save(newCustomer)
        }
    }

    fun getCustomerToppings(email: String): MutableSet<String>? {
        val collect = customerRepository.findByEmail(email)?.getToppings()?.stream()
                ?.map { it: Topping -> it.getToppingName() }
                ?.collect(Collectors.toSet())

        return collect;
    }

    private fun populateToppings(dto: CustomerDto, customer: Customer?) {
        val toppings = dto.toppings.map { name ->
            toppingRepository.findOneByToppingName(name) ?: Topping(toppingName = name)
        }

        toppings.forEach { customer?.addTopping(it) }
    }
}