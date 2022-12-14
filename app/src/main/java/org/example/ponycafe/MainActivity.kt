package org.example.ponycafe

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_PonyCafe)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setup()
    }

    private fun setup() {
        findViewById<Button>(R.id.btnLogin).setOnClickListener{

            val email=findViewById<EditText>(R.id.etEmail).text.toString()
            val pass=findViewById<EditText>(R.id.etPass).text.toString()
            Toast.makeText(this,email + pass, Toast.LENGTH_SHORT).show()

        }
    }

    private fun launchHome(email: String) {
        val mainIntent=Intent(this, StoreMainActivity::class.java).apply {
            putExtra("email",email)
        }
        startActivity(mainIntent)

    }
    private fun alert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Datos erroneos")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog =builder.create()
        dialog.show()
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

    private fun launchHome(s: String) {

    }
