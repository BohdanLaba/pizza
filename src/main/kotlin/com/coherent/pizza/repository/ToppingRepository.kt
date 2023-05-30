package com.coherent.pizza.repository

import com.coherent.pizza.dto.AggregationResponseDto
import com.coherent.pizza.model.Topping
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ToppingRepository : CrudRepository<Topping, Long> {

    @Query("SELECT new com.coherent.pizza.dto.AggregationResponseDto(t.toppingName, COUNT(t.id)) " +
            "FROM Topping AS t GROUP BY t.toppingName ORDER BY t.toppingName ASC ")
    fun aggregateToppings(): List<AggregationResponseDto>
}