package org.example.ponycafe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import org.example.ponycafe.databinding.ActivityNewUserBinding
import java.util.*

class NewUserActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityNewUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding=ActivityNewUserBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
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

        userTypes.addAll(Arrays.asList("Selecciona un tipo de usuario","Estudiante Lenguas","Estudiante Ingenieria/licenciatura", "Personal del Plantel", "Visitantes"))
        viewBinding.spinUsertype.adapter=userTypes
    }

    fun launchLogin(view: View?) {
        val start = Intent(this, MainActivity::class.java)
        startActivity(start)
    }
}