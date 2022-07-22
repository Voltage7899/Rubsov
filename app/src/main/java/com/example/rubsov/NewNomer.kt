package com.example.rubsov

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.rubsov.databinding.ActivityNewNomerBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import kotlin.random.Random

class NewNomer : AppCompatActivity() {
    private lateinit var binding: ActivityNewNomerBinding
    private var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityNewNomerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addNomer.setOnClickListener {

            val id = Random.nextInt(1,100000)

            val nomer=NomerModel(id.toString(),binding.addNameNomer.text.toString(),binding.addPlaces.text.toString(),binding.addPrice.text.toString(),binding.addLocation.text.toString(),binding.addLinkNomer.text.toString())

            database.getReference("Nomer").addListenerForSingleValueEvent(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.child(nomer.id.toString()).exists()){
                        Toast.makeText(this@NewNomer,"Уже есть", Toast.LENGTH_SHORT).show()
                    }
                    else{

                        database.getReference("Nomer").child(id.toString()).setValue(nomer)
                        finish()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        }
        binding.addFirstImage.setOnClickListener {
            try {
                Picasso.get().load(binding.addLinkNomer.text.toString()).fit().into(binding.addFirstImage)
            }catch (ex:Exception){
                Toast.makeText(this,"Нет ссылки на картинку", Toast.LENGTH_SHORT).show()
            }

        }

    }
}