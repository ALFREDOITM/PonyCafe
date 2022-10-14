package org.example.ponycafe

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_PonyCafe)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.boton1).setOnClickListener {
            startStoreMain()
        }
    }

    private fun startStoreMain() {
        val i = Intent(this, StoreMainActivity::class.java)
        startActivity(i)
    }

    fun launchRegister(view: View?) {
        val start = Intent(this, NewUserActivity::class.java)
        startActivity(start)
    }
    fun launchRecover(view: View?) {
        val start = Intent(this, RecoverPassword::class.java)
        startActivity(start)
    }
}