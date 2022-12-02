package org.example.ponycafe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import org.example.ponycafe.R
import org.example.ponycafe.StoreMainActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setup()
    }

    private fun setup(){
        findViewById<Button>(R.id.btnLogin).setOnClickListener{
            //Toast.makeText(this,"hola", Toast.LENGTH_SHORT).show()
            if(findViewById<EditText>(R.id.etEmail).text.isNotEmpty() && findViewById<EditText>(R.id.etPass).text.isNotEmpty()){
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(findViewById<EditText>(R.id.etEmail).text.toString(),
                        findViewById<EditText>(R.id.etPass).text.toString()).addOnCompleteListener {
                        if(it.isSuccessful){
                            launchHome(it.result?.user?.email ?: "")
                        }else{
                            alert()
                        }
                    }
            }else {
                Toast.makeText(this,"Ingresa datos de inicio",Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun launchHome(email:String){
        val mainIntent=Intent(this, StoreMainActivity::class.java).apply {
            putExtra("email",email)
        }
        startActivity(mainIntent)
    }

    private fun alert(){
        val builder =AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Datos erroneos")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog=builder.create()
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
