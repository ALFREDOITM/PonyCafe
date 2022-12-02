package org.example.ponycafe.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.example.ponycafe.database.Cart

@Entity(tableName = TABLE_CART)
data class CartEntity(
    @ColumnInfo(name = "cart_id") @PrimaryKey val uuid: String,
    @ColumnInfo(name = "cart_name") val name: String,
    val cart_cost: Int,
    val cart_quantity: Int,
    val cart_observ: String,
    val cart_img: String
)

fun CartEntity.toCart() = Cart(
    uuid,
    name,
    cart_cost,
    cart_quantity,
    cart_observ,
    cart_img
)