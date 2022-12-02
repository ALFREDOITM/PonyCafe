package org.example.ponycafe.database

import org.example.ponycafe.database.CartEntity

class Cart(
    cart_id: String,
    cart_name: String,
    cart_cost: Int,
    cart_quantity: Int,
    cart_observ: String,
    cart_img: String
) {
    val cart_id: String = cart_id
    val cart_name: String =cart_name
    val cart_cost: Int = cart_cost
    val cart_quantity: Int = cart_quantity
    val cart_observ: String = cart_observ
    val cart_img: String = cart_img
}

fun Cart.toEntity() = CartEntity(
    cart_id,
    cart_name,
    cart_cost,
    cart_quantity,
    cart_observ,
    cart_img
)