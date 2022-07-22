package com.example.rubsov

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.rubsov.databinding.ActivityBuyNomerBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import kotlin.random.Random

class BuyNomer : AppCompatActivity() {
    lateinit var binding: ActivityBuyNomerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityBuyNomerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val item= intent.getSerializableExtra("nomer") as NomerModel
        Picasso.get().load(item.image).fit().into(binding.buyFirstImage)

        binding.buyNameNomer.setText(item.name)
        binding.buyLocation.setText(item.location)

        binding.buyPlaces.setText(item.places)
        binding.buyPrice.setText(item.price)

        binding.buyNomer.setOnClickListener {
            Toast.makeText(this,"Информация выслана в смс",Toast.LENGTH_LONG).show()
        }


    }
}