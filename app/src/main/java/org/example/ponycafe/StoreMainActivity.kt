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

class StoreMainActivity : AppCompatActivity() {
    lateinit var foodGRV: GridView
    lateinit var foodGRVAdapter: GridAdapter
    lateinit var foodList: ArrayList<GridViewModal>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_main)

        //Codigo relacionado al gridview
        foodGRV = findViewById(R.id.idGRV)

        foodList = ArrayList()

        foodGRVAdapter = GridAdapter(this, foodList)

        foodGRV.adapter = foodGRVAdapter

        foodList.add(GridViewModal("Chilaquiles", R.drawable.chilaquiles))
        foodList.add(GridViewModal("Enfrijoladas", R.drawable.enfrijoladas))
        foodList.add(GridViewModal("Sopes", R.drawable.sopes))
        foodList.add(GridViewModal("Tacos", R.drawable.taco))
        foodList.add(GridViewModal("Pizza", R.drawable.pizza))


        foodGRVAdapter.notifyDataSetChanged()
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
                filter(newText)
                return true
            }
        })
        return true
    }

    private fun filter(text: String) {
        // creating a new array list to filter our data.
        val filteredlist: ArrayList<GridViewModal> = ArrayList()

        // running a for loop to compare elements.
        for (item in foodList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.foodName.toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
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
}