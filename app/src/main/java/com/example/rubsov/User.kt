package com.example.rubsov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rubsov.databinding.ActivityUserBinding
import com.google.firebase.database.FirebaseDatabase

class User : AppCompatActivity(),Adapter.ClickListener {
    lateinit var binding: ActivityUserBinding
    private var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    var matchListAdapter:Adapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.recUser.layoutManager= LinearLayoutManager(this)
        matchListAdapter= Adapter(this)
        binding.recUser.adapter=matchListAdapter
        matchListAdapter?.loadListToAdapter(getData())
    }

    fun getData():ArrayList<NomerModel>{



        val matchList=ArrayList<NomerModel>()
        database.getReference("Nomer").get().addOnSuccessListener {
            for (i in it.children){
                var nomer=i.getValue(NomerModel::class.java)
                if(nomer!=null){
                    matchList.add(nomer)
                    matchListAdapter?.loadListToAdapter(matchList)
                }

            }
        }
        return matchList
    }
    override fun onClick(nomer: NomerModel) {
        startActivity(Intent(this, BuyNomer::class.java).apply {
            putExtra("nomer",nomer)

        })
    }
    override fun onStart() {
        super.onStart()
        matchListAdapter?.loadListToAdapter(getData())
    }
}