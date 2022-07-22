package com.example.rubsov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rubsov.databinding.ActivityAdminBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Admin : AppCompatActivity(), Adapter.ClickListener {
    lateinit var binding: ActivityAdminBinding
    private var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    var matchListAdapter:Adapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.newAdmin.setOnClickListener {
            startActivity(Intent(this,NewNomer::class.java))
        }



        binding.recyclerViewAdmin.layoutManager= LinearLayoutManager(this)
        matchListAdapter= Adapter(this)
        binding.recyclerViewAdmin.adapter=matchListAdapter
        matchListAdapter?.loadListToAdapter(getData())

        val simpleCallback =object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val id =matchListAdapter?.deleteItem(viewHolder.adapterPosition)
                database.getReference("Nomer").addListenerForSingleValueEvent(object :
                    ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (id != null) {
                            database.getReference("Nomer").child(id.toString()).removeValue()
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {

                    }

                })
            }

        }
        val itemTouchHelper= ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerViewAdmin)
    }


    fun getData():ArrayList<NomerModel>{



        val List=ArrayList<NomerModel>()
        database.getReference("Nomer").get().addOnSuccessListener {
            for (el in it.children){
                var match=el.getValue(NomerModel::class.java)
                if(match!=null){
                    List.add(match)
                    matchListAdapter?.loadListToAdapter(List)
                }

            }
        }
        return List
    }
    override fun onClick(nomer: NomerModel) {
        startActivity(Intent(this, Update::class.java).apply {
            putExtra("nomer",nomer)

        })
    }

    override fun onStart() {
        super.onStart()
        matchListAdapter?.loadListToAdapter(getData())
    }
}