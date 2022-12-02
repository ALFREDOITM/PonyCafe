package org.example.ponycafe

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Log.e
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.example.ponycafe.databinding.ActivityUserProfileBinding


class UserProfile : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var bindingUser: ActivityUserProfileBinding
    private var uid=""
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingUser=ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(bindingUser.root)
        database=Firebase.database.reference

        val user = Firebase.auth.currentUser
        user?.let {
            uid = user.uid
            //Log.e("TAG",uid)
        }
        Toast.makeText(this,uid,Toast.LENGTH_SHORT).show()
        database=FirebaseDatabase.getInstance().getReference("users")
        database.child(uid).get().addOnSuccessListener {
            if (it.exists()){
                val ponycreditos=it.child("ponycreditos").value
                val name=it.child("name").value
                val email=it.child("email").value
                val fatherLastName=it.child("fatherLN").value
                val motherLastName=it.child("motherLN").value

                bindingUser.etName.text=name.toString()
                bindingUser.etEmail.text=email.toString()
                bindingUser.etCredit.text=ponycreditos.toString()
                bindingUser.etFatherLastName.text=fatherLastName.toString()
                bindingUser.etMotherLastName.text=motherLastName.toString()
                Log.e("TAG",name.toString())
                Log.e("TAG",email.toString())
                Log.e("TAG",ponycreditos.toString())

            }
        }.addOnFailureListener{
            Toast.makeText(this,"Fallo", Toast.LENGTH_SHORT).show()
        }

    }

    fun launchLogin(view: View?) {
        FirebaseAuth.getInstance().signOut()
        val start = Intent(this, MainActivity::class.java)
        startActivity(start)
    }

    fun changePass(view: View?) {
        val start = Intent(this, ChangePassword::class.java)
        startActivity(start)
    }
}