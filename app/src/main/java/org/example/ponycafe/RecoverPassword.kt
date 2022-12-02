package org.example.ponycafe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import org.example.ponycafe.databinding.ActivityRecoverPasswordBinding

class RecoverPassword : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityRecoverPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityRecoverPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recover()
    }

    private fun recover() {
        binding.btnRecover.setOnClickListener {
            var mail=binding.etEmail.text.toString()
            Toast.makeText(this, mail.toString(),Toast.LENGTH_SHORT).show()
            if(mail.isNotEmpty()){

                auth.setLanguageCode("es")
                Log.e("TAG","Success1")
                auth.sendPasswordResetEmail(mail).addOnCompleteListener {
                    Log.e("TAG","Success")
                    if(it.isSuccessful){
                        Log.e("TAG","sEMD")
                        Toast.makeText(this,"Se envio un correo electronico para recuperar la contraseña a"+ mail.toString(),Toast.LENGTH_SHORT).show()
                        Log.e("TAG",mail)
                        val start = Intent(this, LoginActivity::class.java)
                        startActivity(start)
                        finish()
                    }
                }
            }else{
                Toast.makeText(this,"Ingresa el correo",Toast.LENGTH_SHORT).show()
            }
        }
    }

}