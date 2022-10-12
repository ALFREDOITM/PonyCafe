package org.example.ponycafe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class NewUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_user)
    }

    fun launchLogin(view: View?) {
        val start = Intent(this, MainActivity::class.java)
        startActivity(start)
    }
}