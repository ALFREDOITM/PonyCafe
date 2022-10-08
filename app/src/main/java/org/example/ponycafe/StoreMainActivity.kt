package org.example.ponycafe

import android.os.Bundle
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class StoreMainActivity : AppCompatActivity() {
    lateinit var courseGRV: GridView
    lateinit var courseList: List<GridViewModal>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_main)

        courseGRV = findViewById(R.id.idGRV)
        courseList = ArrayList<GridViewModal>()

        // on below line we are adding data to
        // our course list with image and course name.
        courseList = courseList + GridViewModal("Chilaquiles", R.drawable.chilaquiles)
        courseList = courseList + GridViewModal("Enfrijoladas", R.drawable.enfrijoladas)
        courseList = courseList + GridViewModal("Sopes", R.drawable.sopes)
        courseList = courseList + GridViewModal("Tacos", R.drawable.taco)
        courseList = courseList + GridViewModal("Pizza", R.drawable.pizza)

        // on below line we are initializing our course adapter
        // and passing course list and context.
        val courseAdapter = GridAdapter(courseList = courseList, this@StoreMainActivity)

        // on below line we are setting adapter to our grid view.
        courseGRV.adapter = courseAdapter

        // on below line we are adding on item
        // click listener for our grid view.
        courseGRV.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            // inside on click method we are simply displaying
            // a toast message with course name.
            Toast.makeText(
                applicationContext, courseList[position].courseName + " selected",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}