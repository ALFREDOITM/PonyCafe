package org.example.ponycafe

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject

class SelectedItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_item)

        val foodInfo: Array<JSONObject> = arrayOf(
            JSONObject("{\"comida\": \"Chilaquiles\", \"descripcion\": \"Tortilla de maiz triangular bañada en salsa acompañado de una guarnicion\"}"),
            JSONObject("{\"comida\": \"Enfrijoladas\", \"descripcion\": \"Tortilla de maiz cubierta en salsa de frijol con relleno de queso y cebolla\"}"),
            JSONObject("{\"comida\": \"Sopes\", \"descripcion\": \"Disco de masa de maiz con relleno a elegir\"}"),
            JSONObject("{\"comida\": \"Tacos\", \"descripcion\": \"Tortilla de maiz con relleno de proteina carnica a elegir\"}"),
            JSONObject("{\"comida\": \"Pizza\", \"descripcion\": \"Disco con rebanadas triangulares las cuales vienen en una caja cuadrada\"}")
        )

       val item = intent.getIntExtra("item",1)

        if (item == 0)
            findViewById<ImageView>(R.id.itemIV1).setImageResource(R.drawable.chilaquiles)
        else if (item == 1)
            findViewById<ImageView>(R.id.itemIV1).setImageResource(R.drawable.enfrijoladas)
        else if (item == 2)
            findViewById<ImageView>(R.id.itemIV1).setImageResource(R.drawable.sopes)
        else if (item == 3)
            findViewById<ImageView>(R.id.itemIV1).setImageResource(R.drawable.taco)
        else if (item == 4)
            findViewById<ImageView>(R.id.itemIV1).setImageResource(R.drawable.pizza)

        findViewById<TextView>(R.id.itemTV1).text = foodInfo[item].getString("comida")
        findViewById<TextView>(R.id.itemTV2).text = foodInfo[item].getString("descripcion")

    }
}