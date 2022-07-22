package com.example.rubsov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.rubsov.databinding.ActivityUpdateBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso

class Update : AppCompatActivity() {
    lateinit var binding: ActivityUpdateBinding
    private var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val item = intent.getSerializableExtra("nomer") as NomerModel

        Picasso.get().load(item.image).fit().into(binding.updateFirstImage)

        binding.updateLinkNomer.setText(item.image)
        binding.updateNameNomer.setText(item.name)
        binding.updateLocation.setText(item.location)

        binding.updatePlaces.setText(item.places)
        binding.updatePrice.setText(item.price)



        binding.updateNomer.setOnClickListener {
            val nomer=NomerModel(item.id,binding.updateNameNomer.text.toString(),binding.updatePlaces.text.toString(),binding.updatePrice.text.toString(),binding.updateLocation.text.toString(),binding.updateLinkNomer.text.toString())
            database.getReference("Nomer").addListenerForSingleValueEvent(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.child(nomer.id.toString()).exists()){
                        Toast.makeText(this@Update,"Такая запись уже существует", Toast.LENGTH_SHORT).show()
                    }
                    else{

                        database.getReference("Nomer").child(item.id.toString()).setValue(nomer)
                        finish()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        }
        binding.updateFirstImage.setOnClickListener {
            try {
                Picasso.get().load(binding.updateLinkNomer.text.toString()).fit().into(binding.updateFirstImage)
            }catch (ex:Exception){
                Toast.makeText(this,"Нет ссылки на картинку", Toast.LENGTH_SHORT).show()
            }

        }

    }
}