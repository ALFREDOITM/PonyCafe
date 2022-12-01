package org.example.ponycafe.database

import androidx.room.*
import org.example.ponycafe.database.CartEntity

@Dao
interface CartDao {
    @Query("SELECT * FROM $TABLE_CART")
    fun getCartFromDatabase(): List<CartEntity>

    @Query("SELECT * FROM $TABLE_CART WHERE cart_id = :uuid")
    fun getCartByUuid(uuid: String): CartEntity

    @Delete
    fun delete(cart: CartEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(cart: CartEntity)

}