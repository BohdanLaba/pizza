package com.coherent.pizza.controller

import com.coherent.pizza.dto.CustomerDto
import com.coherent.pizza.model.Customer
import com.coherent.pizza.model.Topping
import com.coherent.pizza.service.CustomerService
import com.coherent.pizza.service.ToppingService
import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*

@WebMvcTest
class CustomerControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    lateinit var customerService: CustomerService

    @MockkBean
    lateinit var toppingService: ToppingService

    @Test
    fun submitSurveyTest() {
        //given
        val mapper = ObjectMapper()
        val toppings = Collections.singleton("Calzone")
        val dto = CustomerDto("user@mail.com", toppings)
        val customer = Customer(0, "user@mail.com", Collections.singleton(Topping("Calzone")))
        every { customerService.submit(dto) } just Runs
        //when
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/customer/survey")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isNoContent())
        verify { customerService.submit(dto) }
    }

    @Test
    fun getCustomerToppingsTest() {
        //given
        val mail = "user@mail.com"
        val topping = "Quattro formaggi"
        every { customerService.getCustomerToppings(mail) } returns mutableSetOf(topping)
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customer/user@mail.com")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").value(topping))

        verify { customerService.getCustomerToppings(mail) }
    }
}