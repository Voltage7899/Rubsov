package com.example.rubsov

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rubsov.databinding.ElRecBinding
import com.squareup.picasso.Picasso

class Adapter(val clickListener: ClickListener) : RecyclerView.Adapter<Adapter.RaceViewHolder>() {

    private var raceListInAdapter= ArrayList<NomerModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.RaceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.el_rec,parent,false)

        return RaceViewHolder(view)
    }

    override fun onBindViewHolder(holder: Adapter.RaceViewHolder, position: Int) {
        holder.bind(raceListInAdapter[position],clickListener)
    }

    override fun getItemCount(): Int {
        return raceListInAdapter.size
    }

    class RaceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ElRecBinding.bind(itemView)
        fun bind(nomer: NomerModel, clickListener: ClickListener){
            binding.elNameNomer.text=nomer.name
            binding.elLocation.text=nomer.location
            binding.elPlaces.text=nomer.places
            binding.elPrice.text=nomer.price
            Picasso.get().load(nomer.image).fit().into(binding.elFirstImage)

            itemView.setOnClickListener{

                clickListener.onClick(nomer)
            }

        }
    }
    fun loadListToAdapter(productList:ArrayList<NomerModel>){
        raceListInAdapter= productList
        notifyDataSetChanged()
    }

    interface ClickListener{
        fun onClick(nomer: NomerModel){

        }
    }
    fun deleteItem(i:Int):String?{
        var id=raceListInAdapter.get(i).id

        raceListInAdapter.removeAt(i)

        notifyDataSetChanged()

        return id
    }

}