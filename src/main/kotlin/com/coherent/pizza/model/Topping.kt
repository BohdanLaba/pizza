package com.coherent.pizza.model

import javax.persistence.*

@Entity
@Table(name = "TOPPINGS")
class Topping(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private var id: Long = 0,

        @Column(name = "topping_name", nullable = false)
        private var toppingName: String,

        @ManyToMany(mappedBy = "toppings")
        private var customers: MutableSet<Customer>


) {
    constructor(toppingName: String) :
            this(0, toppingName, mutableSetOf()) {
    }

    fun getToppingName(): String {
        return this.toppingName;
    }

}