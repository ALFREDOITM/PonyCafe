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

class StoreMainActivity : AppCompatActivity() {
    lateinit var foodGRV: GridView
    lateinit var foodList: List<GridViewModal>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_main)

        //Codigo relacionado al gridview
        foodGRV = findViewById(R.id.idGRV)
        val foodAdapter = obtenerElementosGridView()
        foodGRV.adapter = foodAdapter
        foodGRV.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            /*
            Toast.makeText(
                applicationContext, foodList[position].foodName + " selected",
                Toast.LENGTH_SHORT
            ).show()
            */
            val intent = Intent(this, SelectedItemActivity::class.java)
            intent.putExtra("item", position)
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
                var gridAdapter: GridAdapter

                return true
            }
        })
        return true
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
    private fun obtenerElementosGridView(): GridAdapter {
        foodList = ArrayList()

        // on below line we are adding data to
        // our food list with image and food name.
        foodList = foodList + GridViewModal("Chilaquiles", R.drawable.chilaquiles)
        foodList = foodList + GridViewModal("Enfrijoladas", R.drawable.enfrijoladas)
        foodList = foodList + GridViewModal("Sopes", R.drawable.sopes)
        foodList = foodList + GridViewModal("Tacos", R.drawable.taco)
        foodList = foodList + GridViewModal("Pizza", R.drawable.pizza)

        return GridAdapter(foodList = foodList, this@StoreMainActivity, foodList)
    }
}