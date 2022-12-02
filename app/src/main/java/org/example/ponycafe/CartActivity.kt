package org.example.ponycafe

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

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

        getCartData()

        findViewById<Button>(R.id.btn67).setOnClickListener{
            getData()
        }
    }

    private fun getData() {
        var uid = ""
        var productos = ""
        var cantidad = ""

        val user = Firebase.auth.currentUser
        user?.let {
             uid = user.uid
            //Log.e("TAG",uid)
        }

        dbref = FirebaseDatabase.getInstance().getReference("cart")
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    var i = 0
                    for (cartSnapshot in snapshot.children){
                        val cart = cartSnapshot.getValue(CartModal::class.java)
                        newArrayList.add(cart!!)
                        total = total + (newArrayList[i].cost!! * newArrayList[i].quantity!!)
                        productos = productos + newArrayList[i].name + "   "
                        cantidad = cantidad + newArrayList[i].quantity.toString() + "              "
                        i += 1
                    }
                    this@CartActivity.uploadData(uid, productos, cantidad, total)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
    private fun uploadData(uid: String, productos: String, cantidad: String, total: Int) {
        dbref = FirebaseDatabase.getInstance().getReference("orders")
        val item = OrderModal(uid, productos, cantidad, total)

        dbref.child(uid).setValue(item).addOnSuccessListener {
            Toast.makeText(this, "Subido de manera exitosa", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        }

    }
    private fun getCartData() {
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
                    findViewById<TextView>(R.id.tv567).text = total.toString()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}