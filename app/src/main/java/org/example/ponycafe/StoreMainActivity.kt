package org.example.ponycafe

import android.os.Bundle
import android.view.Menu
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class StoreMainActivity : AppCompatActivity() {
    lateinit var courseGRV: GridView
    lateinit var courseList: List<GridViewModal>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_main)

        //Codigo relacionado al gridview
        courseGRV = findViewById(R.id.idGRV)
        val courseAdapter = obtenerElementosGridView()
        courseGRV.adapter = courseAdapter
        courseGRV.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            Toast.makeText(
                applicationContext, courseList[position].courseName + " selected",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_user, menu)
        return true
    }

    private fun obtenerElementosGridView(): GridAdapter {
        courseList = ArrayList()

        // on below line we are adding data to
        // our course list with image and course name.
        courseList = courseList + GridViewModal("Chilaquiles", R.drawable.chilaquiles)
        courseList = courseList + GridViewModal("Enfrijoladas", R.drawable.enfrijoladas)
        courseList = courseList + GridViewModal("Sopes", R.drawable.sopes)
        courseList = courseList + GridViewModal("Tacos", R.drawable.taco)
        courseList = courseList + GridViewModal("Pizza", R.drawable.pizza)

        return GridAdapter(courseList = courseList, this@StoreMainActivity)
    }
}