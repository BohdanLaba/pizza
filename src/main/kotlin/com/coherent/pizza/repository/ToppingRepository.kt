package com.coherent.pizza.repository

import com.coherent.pizza.dto.AggregationResponseDto
import com.coherent.pizza.model.Topping
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ToppingRepository : CrudRepository<Topping, Long> {

    fun findOneByToppingName(name: String): Topping?

    @Query("SELECT new com.coherent.pizza.dto.AggregationResponseDto(t.toppingName, COUNT(c)) " +
            "FROM Topping AS t JOIN t.customers c GROUP BY t.toppingName  ORDER BY t.toppingName ASC ")
    fun aggregateToppings(): List<AggregationResponseDto>
}