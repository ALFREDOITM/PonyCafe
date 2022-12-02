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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        newRecyclerView = findViewById(R.id.rvCartEntries)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf<CartModal>()

        newArrayList.add(CartModal())
        newArrayList.add(CartModal())
        newArrayList.add(CartModal())
        newArrayList.add(CartModal())
        newArrayList.add(CartModal())

        newRecyclerView.adapter = RecyclerAdapter(newArrayList)
    }
}