package com.example.rubsov

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.rubsov.databinding.ActivityRegistrationBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Registration : AppCompatActivity() {
    private lateinit var binding:ActivityRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.regReg.setOnClickListener {
            val database = FirebaseDatabase.getInstance().getReference("User")
            val user=UserModel(binding.nameReg.text.toString(),binding.phoneReg.text.toString(),binding.passReg.text.toString())
            database.addListenerForSingleValueEvent(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (!snapshot.child(binding.phoneReg.text.toString()).exists()) {
                        database.child(binding.phoneReg.text.toString()).setValue(user)
                        Toast.makeText(this@Registration, "Вы зарегистрированы", Toast.LENGTH_SHORT)
                            .show()
                        finish()
                    } else {
                        Toast.makeText(
                            this@Registration,
                            "Пользователь с такими данными уже есть",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
        }
    }
}