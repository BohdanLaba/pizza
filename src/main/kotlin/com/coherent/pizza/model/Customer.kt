package com.coherent.pizza.model

import javax.persistence.*

@Entity
@Table(name = "CUSTOMERS")
class Customer(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private var id: Long = 0,

        @Column(name = "email", nullable = false, unique = true)
        private var email: String,

        @ManyToMany(cascade = [CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH],
                fetch = FetchType.LAZY)
        @JoinTable(name = "customer_topping",
                joinColumns = [JoinColumn(name = "customer_id")],
                inverseJoinColumns = [JoinColumn(name = "topping_id")])
        private var toppings: MutableSet<Topping>
) {
    fun addTopping(topping: Topping) {
        toppings.add(topping)
    }

    fun getToppings(): MutableSet<Topping> {
        return toppings
    }
}