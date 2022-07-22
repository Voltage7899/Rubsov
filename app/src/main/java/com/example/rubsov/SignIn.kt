package com.example.rubsov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rubsov.databinding.ActivitySignInBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SignIn : AppCompatActivity() {
    private lateinit var binding:ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signSign.setOnClickListener {
            val database = FirebaseDatabase.getInstance().getReference("User")
            if (TextUtils.isEmpty(binding.phoneSign.text.toString()) && TextUtils.isEmpty(binding.passSign.text.toString())) {
                Toast.makeText(this@SignIn, "Введите все данные", Toast.LENGTH_SHORT).show()
            } else {
                database.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.child(binding.phoneSign.text.toString()).exists()) {
                            val userCurrentData: UserModel? = snapshot.child(binding.phoneSign.text.toString()).getValue(
                                UserModel::class.java
                            )
                            UserModel.currentUser = userCurrentData
                            if (userCurrentData?.phone.equals(binding.phoneSign.text.toString()) && userCurrentData?.pass.equals(binding.passSign.text.toString())) {
                                Toast.makeText(this@SignIn, "Вы вошли как Юзер", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this@SignIn, User::class.java)
                                startActivity(intent)
                            } else {
                                Toast.makeText(this@SignIn, "Неверные данные", Toast.LENGTH_SHORT).show()
                            }
                        }
                        else{
                            Toast.makeText(this@SignIn, "Номера не существует", Toast.LENGTH_SHORT).show()

                        }
                    }

                    override fun onCancelled(error: DatabaseError) {}
                })
            }
        }
        binding.adminSign.setOnClickListener {
            val database = FirebaseDatabase.getInstance().getReference("Admin")
            if (TextUtils.isEmpty(binding.phoneSign.text.toString()) && TextUtils.isEmpty(binding.passSign.text.toString())) {
                Toast.makeText(this@SignIn, "Введите все данные", Toast.LENGTH_SHORT).show()
            } else {
                database.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.child(binding.phoneSign.text.toString()).exists()) {
                            val userCurrentData: UserModel? = snapshot.child(binding.phoneSign.text.toString()).getValue(UserModel::class.java)
                                UserModel.currentUser = userCurrentData

                            if (userCurrentData?.phone.equals(binding.phoneSign.text.toString()) && userCurrentData?.pass.equals(
                                    binding.passSign.text.toString()))
                            {
                                Toast.makeText(this@SignIn, "Вы вошли как админ", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this@SignIn, Admin::class.java)
                                startActivity(intent)
                            } else {
                                Toast.makeText(this@SignIn, "Неверные данные", Toast.LENGTH_SHORT).show()
                            }
                        }
                        else{
                            Toast.makeText(this@SignIn, "Номера не существует", Toast.LENGTH_SHORT).show()

                        }
                    }

                    override fun onCancelled(error: DatabaseError) {}
                })
            }
        }
    }
}