package org.example.ponycafe

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class CartActivity: AppCompatActivity() {

    private  lateinit var dbref: DatabaseReference
    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<CartModal>
    var total = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        newRecyclerView = findViewById(R.id.rvCartEntries)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf<CartModal>()

        getUserData()
    }

    private fun getUserData() {
        dbref = FirebaseDatabase.getInstance().getReference("cart")
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    var i = 0
                    for (cartSnapshot in snapshot.children){
                        val cart = cartSnapshot.getValue(CartModal::class.java)
                        newArrayList.add(cart!!)
                        total = total + (newArrayList[i].cost!! * newArrayList[i].quantity!!)
                        i += 1
                    }
                    newRecyclerView.adapter = RecyclerAdapter(newArrayList)
                    findViewById<TextView>(R.id.tv567).text = "TOTAL: $" + total.toString()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}