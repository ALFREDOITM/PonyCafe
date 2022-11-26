package org.example.ponycafe

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import android.widget.SearchView
import android.widget.Toast
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage

class StoreMainActivity : AppCompatActivity() {
    lateinit var foodGRV: GridView
    lateinit var foodGRVAdapter: GridAdapter
    lateinit var foodList: ArrayList<MenuModal>
    lateinit var dbref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_main)

        foodGRV = findViewById(R.id.idGRV)
        foodList = ArrayList()
        foodGRVAdapter = GridAdapter(this, foodList)
        foodGRV.adapter = foodGRVAdapter

        getMenuData()

        foodGRVAdapter.notifyDataSetChanged()
        foodGRV.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            /*
            Toast.makeText(
                applicationContext, foodList[position].foodName + " selected",
                Toast.LENGTH_SHORT
            ).show()
            */
            val intent = Intent(this, SelectedItemActivity::class.java)
            intent.putExtra("name", foodList[position].name)
            intent.putExtra("desc", foodList[position].desc)
            intent.putExtra("cost", foodList[position].cost)
            intent.putExtra("img", foodList[position].img)
            startActivityForResult(intent,1)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_user, menu)
        val menuItem = menu.findItem(R.id.menu_buscar)
        val searchView: SearchView = menuItem.actionView as SearchView
        searchView.maxWidth = Int.MAX_VALUE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                Log.e("TAG", " new text ==> $newText")
                filter(newText)
                return true
            }
        })
        return true
    }

    private fun filter(text: String) {
        // creating a new array list to filter our data.
        val filteredlist: ArrayList<MenuModal> = ArrayList()
        for (item in foodList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.name?.toLowerCase()!!.contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            foodGRVAdapter.filterList(filteredlist)
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            foodGRVAdapter.filterList(filteredlist)
        }

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.shopping_cart -> {
                lanzarShoppingCart()
                true
            }
            R.id.user_profile -> {
                lanzarUserProfile()
                true
            }
            else -> super.onOptionsItemSelected(item)

        }
    }

    fun lanzarUserProfile(view: View? = null) {
        val i = Intent(this, UserProfile::class.java)
        startActivity(i)
    }
    fun lanzarShoppingCart(view: View? = null) {
        val i = Intent(this, CartActivity::class.java)
        startActivity(i)
    }

    private fun getMenuData(){
        dbref = FirebaseDatabase.getInstance().getReference("menu")
        dbref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (menuSnapshot in snapshot.children){
                        val menu = menuSnapshot.getValue(MenuModal::class.java)
                        foodList.add(menu!!)
                    }
                }
                foodGRVAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
    private fun getMenuData2(){
        dbref = FirebaseDatabase.getInstance().getReference("menu")
    }
}