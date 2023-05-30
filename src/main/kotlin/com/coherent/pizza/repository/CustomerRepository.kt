package com.coherent.pizza.repository

import com.coherent.pizza.model.Customer
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : CrudRepository<Customer, Long> {

    fun findOneByEmail(email: String): Customer?

//    @EntityGraph(value = "customer-toppings-graph")
    @EntityGraph(attributePaths = ["toppings"])
    fun findByEmail(email: String): Customer?
}