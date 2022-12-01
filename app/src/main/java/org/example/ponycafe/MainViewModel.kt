package org.example.ponycafe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.example.ponycafe.database.DatabaseManager
import org.example.ponycafe.database.MyCoroutines
import org.example.ponycafe.database.Cart
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    fun saveCart(cart: Cart){
        viewModelScope.launch {
            val cartDao = DatabaseManager.instance.database.cartDao()
            MyCoroutines(cartDao).save(cart)
        }
    }
    fun deleteCart(cart: Cart){
        viewModelScope.launch {
            val cartDao = DatabaseManager.instance.database.cartDao()
            MyCoroutines(cartDao).delete(cart)
        }
    }
    val savedCart = MutableLiveData<List<Cart>>()
    fun getCart(){
        viewModelScope.launch {
            val cartDao = DatabaseManager.instance.database.cartDao()
            savedCart.value = MyCoroutines(cartDao).getCart().value
        }
    }
}