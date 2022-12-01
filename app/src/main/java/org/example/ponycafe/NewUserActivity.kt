package org.example.ponycafe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.example.ponycafe.databinding.ActivityNewUserBinding
import java.util.*

class NewUserActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityNewUserBinding
    private lateinit var database : DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding=ActivityNewUserBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        auth = FirebaseAuth.getInstance()
        val userTypes=ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_dropdown_item)

        val questions1=ArrayAdapter<String>(this,
        android.R.layout.simple_spinner_dropdown_item)

        val questions2=ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_dropdown_item)

        questions1.addAll((Arrays.asList("Selecciona una pregunta","¿En qué lugar nació tu madre?",
        "¿En qué primaria ibas?",
        "¿A que edad fue tu primer beso?",
        "Nombre de tu primer amor.",
        "Nombre de tu mejor amigo.")))
        viewBinding.spinSecureQuestions.adapter=questions1

        questions2.addAll((Arrays.asList("Selecciona una pregunta","¿En qué lugar nació tu padre?",
            "¿En qué preparatoria ibas?",
            "¿Cuál es tu animal favorito ?",
            "Nombre de tu primer ex.",
            "Nombre de tu primer mascota.")))
        viewBinding.spinSecureQuestions2.adapter=questions2

        userTypes.addAll(Arrays.asList("Selecciona un tipo de usuario","Estudiante","Personal del Plantel" ))
        viewBinding.spinUsertype.adapter=userTypes

        viewBinding.btnRegister.setOnClickListener{
            registerUserAuth()
        }
    }
    private fun registerUserAuth(){
        val email = viewBinding.etEmail.text.toString()
        val pass = viewBinding.etPassword.text.toString()
        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "createUserWithEmail:success")
                    val user = auth.currentUser
                    val uid = auth.uid.toString()
                    //updateUI(user)
                    registerUser(uid)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Usuario existente / Contraseña debil",
                        Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }
            }
    }
    private fun registerUser(uid: String) {
        val name = viewBinding.etName.text.toString()
        val fatherLN = viewBinding.etFatherLastName.text.toString()
        val motherLN = viewBinding.etMotherLastName.text.toString()
        val bDate = viewBinding.calendarView.date.toString()
        val user = viewBinding.etUser.text.toString().uppercase(Locale.getDefault())
        val pass = "a2352bhbjhj"
        val type = viewBinding.spinUsertype.selectedItem.toString()
        val email = viewBinding.etEmail.text.toString()
        val quest1 = viewBinding.spinSecureQuestions.selectedItem.toString()
        val answ1 = viewBinding.etAnswer.text.toString()
        val quest2 = viewBinding.spinSecureQuestions2.selectedItem.toString()
        val answ2 = viewBinding.etAnswer2.text.toString()

        database = FirebaseDatabase.getInstance().getReference("users")
        val User = User(name, fatherLN, motherLN, bDate, user, pass, type, email, quest1, answ1, quest2, answ2)
        database.child(uid).setValue(User).addOnSuccessListener {
            Toast.makeText(this, "usuario creado con exito",Toast.LENGTH_SHORT).show()
            val start = Intent(this, MainActivity::class.java)
            startActivity(start)
        }.addOnFailureListener{
            Toast.makeText(this, "Error",Toast.LENGTH_SHORT).show()
        }

        //myRef.child("users").child(id.toString()).setValue(NewUser("Alfredo", "Sanchez","Izquierdo","alfre", "pass123","alfred@pony.com"))
        //id += 1
    }
}