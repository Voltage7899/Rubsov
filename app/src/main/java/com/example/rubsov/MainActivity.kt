package com.example.rubsov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rubsov.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.reg.setOnClickListener {
            startActivity(Intent(this,Registration::class.java))
        }
        binding.sing.setOnClickListener {
            startActivity(Intent(this,SignIn::class.java))
        }
    }
}