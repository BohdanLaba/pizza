package com.coherent.pizza.controller

import com.coherent.pizza.dto.AggregationResponseDto
import com.coherent.pizza.service.CustomerService
import com.coherent.pizza.service.ToppingService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import java.util.*

@WebMvcTest
class ToppingControllerTest(@Autowired val mockMvc: MockMvc) {
    @MockkBean
    lateinit var customerService: CustomerService

    @MockkBean
    lateinit var toppingService: ToppingService

    @Test
    fun aggregateTest() {
        //given
        val aggregateDto = Collections.singletonList(AggregationResponseDto("Calzone", 4))
        every { toppingService.aggregateAllToppings() } returns aggregateDto
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/topping")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$[0].count").value(4))
    }
}