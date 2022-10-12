package org.example.ponycafe

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject

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
        return true
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

        return GridAdapter(foodList = foodList, this@StoreMainActivity)
    }
}