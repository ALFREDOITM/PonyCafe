package org.example.ponycafe

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import androidx.activity.viewModels

class SelectedItemActivity : AppCompatActivity() {
    private var menuModal2: MenuModal2? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_item)

        var name = ""
        var desc = ""
        var cost = 0
        var img = ""

        if (intent.extras != null) {
            menuModal2 = intent.getSerializableExtra("data") as MenuModal2?
            Log.e("PASSED", "===> ${menuModal2?.name}")
            name = menuModal2?.name.toString()
            desc = menuModal2?.desc.toString()
            cost = menuModal2?.cost!!
            img = menuModal2?.img.toString()
            findViewById<TextView>(R.id.itemTV1).text = name
            findViewById<TextView>(R.id.itemTV2).text = desc
            findViewById<TextView>(R.id.itemTV3).text = "$" + (cost.toString())
            val imgVW = findViewById<ImageView>(R.id.itemIV1)
            Picasso.get().load(img).into(imgVW)
        }
        findViewById<Button>(R.id.removeBtn1).setOnClickListener{
            var num = findViewById<TextView>(R.id.itemQuanEt1).text.toString().toInt()
            if(num != 0){
                num -= 1
                findViewById<TextView>(R.id.itemQuanEt1).text = num.toString()
            }
        }
        findViewById<Button>(R.id.addBtn1).setOnClickListener{
            var num = findViewById<TextView>(R.id.itemQuanEt1).text.toString().toInt()
            num += 1
            findViewById<TextView>(R.id.itemQuanEt1).text = num.toString()
        }
        //click listener para el boton de mandar a carrito
        findViewById<Button>(R.id.itemBTN1).setOnClickListener{

        }
    }
}