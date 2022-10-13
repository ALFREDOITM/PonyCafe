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
            JSONObject("{\"comida\": \"Chilaquiles\", \"descripcion\": \"Tortilla de maiz triangular bañada en salsa acompañado de una guarnicion\", \"costo\": \"30\"}"),
            JSONObject("{\"comida\": \"Enfrijoladas\", \"descripcion\": \"Tortilla de maiz cubierta en salsa de frijol con relleno de queso y cebolla\", \"costo\": \"40\"}"),
            JSONObject("{\"comida\": \"Sopes\", \"descripcion\": \"Disco de masa de maiz con relleno a elegir\", \"costo\": \"25\"}"),
            JSONObject("{\"comida\": \"Tacos\", \"descripcion\": \"Tortilla de maiz con relleno de proteina carnica a elegir\", \"costo\": \"16\"}"),
            JSONObject("{\"comida\": \"Pizza\", \"descripcion\": \"Disco con rebanadas triangulares las cuales vienen en una caja cuadrada\", \"costo\": \"100\"}")
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

        var cost: String = "Costo: $"
        cost += foodInfo[item].getString("costo")
        findViewById<TextView>(R.id.itemTV1).text = foodInfo[item].getString("comida")
        findViewById<TextView>(R.id.itemTV2).text = foodInfo[item].getString("descripcion")
        findViewById<TextView>(R.id.itemTV3).text = cost

    }
}