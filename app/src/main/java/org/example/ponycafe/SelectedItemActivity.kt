package org.example.ponycafe

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import org.json.JSONObject

class SelectedItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_item)
        val name= intent.getStringExtra("name")
        val desc = intent.getStringExtra("desc")
        val cost = intent.getIntExtra("cost",1)
        val img = intent.getStringExtra("img")

        findViewById<TextView>(R.id.itemTV1).text = name
        findViewById<TextView>(R.id.itemTV2).text = desc
        findViewById<TextView>(R.id.itemTV3).text = cost.toString()
        val imgVW = findViewById<ImageView>(R.id.itemIV1)
        Picasso.get().load(img).into(imgVW)

    }
}