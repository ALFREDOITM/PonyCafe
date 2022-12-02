package org.example.ponycafe

import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import org.example.ponycafe.databinding.ActivitySelectedItemBinding

class SelectedItemActivity : AppCompatActivity() {
    lateinit var binding : ActivitySelectedItemBinding
    lateinit var database : DatabaseReference
    private var menuModal2: MenuModal2? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectedItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
            binding.itemTV1.text = name
            binding.itemTV2.text = desc
            binding.itemTV3.text = "$" + (cost.toString())
            Picasso.get().load(img).into(binding.itemIV1)
        }
        binding.removeBtn1.setOnClickListener{
            var num = binding.itemQuanEt1.text.toString().toInt()
            if(num != 0){
                num -= 1
                binding.itemQuanEt1.text = num.toString()
            }
        }
        binding.addBtn1.setOnClickListener{
            var num = binding.itemQuanEt1.text.toString().toInt()
            num += 1
            binding.itemQuanEt1.text = num.toString()
        }
        //click listener para el boton de mandar a carrito
        binding.itemBTN1.setOnClickListener{
            var quant = binding.itemQuanEt1.text.toString()
            var obser = binding.editTextTextMultiLine2.text.toString()
            val user = Firebase.auth.currentUser
            user?.let {
                val uid = user.uid
                //Log.e("TAG",uid)
                uploadData(uid, name, cost, quant, obser, img)
            }
        }
    }
    private fun uploadData(
        uid: String,
        name: String,
        cost: Int,
        quant: String,
        obser: String,
        img: String
    ) {

        database = FirebaseDatabase.getInstance().getReference("cart")
        val item = CartModal(uid, name, cost, quant.toInt(), obser, img)

        database.child(name).setValue(item).addOnSuccessListener {
            Toast.makeText(this, "Agregado a carrito", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        }
    }
}