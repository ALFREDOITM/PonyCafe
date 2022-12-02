package org.example.ponycafe

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler

class CartActivity: AppCompatActivity() {

    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<CartModal>
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        newRecyclerView = findViewById(R.id.rvCartEntries)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf<CartModal>()


        mainViewModel.getCart()
        //Nota:
        //si es en un fragment
        //es con viewLifecycleOwner en lugar de this
        mainViewModel.savedCart.observe(this, {cartList ->
            if(!cartList.isNullOrEmpty()){
                for(cart in cartList){
                    //Log.d("thesearetheusers", cart.cart_name)
                    newArrayList.add(CartModal(cart.cart_name, cart.cart_cost, cart.cart_quantity, cart.cart_observ, cart.cart_img))
                }
            }else{
                Log.d("thesearetheusers", "null or empty")
            }
        } )
    }
}