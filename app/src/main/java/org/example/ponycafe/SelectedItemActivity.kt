package org.example.ponycafe

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso


class SelectedItemActivity : AppCompatActivity() {
    private var menuModal2: MenuModal2? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_item)

        if (intent.extras != null) {
            menuModal2 = intent.getSerializableExtra("data") as MenuModal2?
            Log.e("PASSED", "===> ${menuModal2?.name}")
            findViewById<TextView>(R.id.itemTV1).text = menuModal2?.name
            findViewById<TextView>(R.id.itemTV2).text = menuModal2?.desc
            findViewById<TextView>(R.id.itemTV3).text = "$" + menuModal2?.cost.toString()
            val imgVW = findViewById<ImageView>(R.id.itemIV1)
            Picasso.get().load(menuModal2?.img).into(imgVW)
        }

    }
}