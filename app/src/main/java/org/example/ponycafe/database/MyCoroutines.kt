package org.example.ponycafe.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MyCoroutines(private val cartDao: CartDao) {
    suspend fun save(cart: Cart) = withContext(Dispatchers.IO){
        cartDao.save(cart.toEntity())
    }

    suspend fun delete(cart: Cart) = withContext(Dispatchers.IO){
        cartDao.delete(cart.toEntity())
    }

    suspend fun getCart(): LiveData<List<Cart>> = withContext(Dispatchers.IO){
        return@withContext MutableLiveData(cartDao.getCartFromDatabase().map { eachCartEntity ->
        eachCartEntity.toCart() })
    }
}