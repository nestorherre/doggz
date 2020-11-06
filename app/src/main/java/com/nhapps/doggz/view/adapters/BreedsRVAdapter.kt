package com.nhapps.doggz.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nhapps.doggz.R
import kotlinx.android.synthetic.main.rv_breeds_item.view.*

class BreedsRVAdapter(private val listener: (String) -> Unit): RecyclerView.Adapter<BreedsRVAdapter.MyViewHolder>() {

    private var usedList = emptyList<String>()
    private var initialList = emptyList<String>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.rv_breeds_item,
            parent,
            false
        )
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return usedList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = usedList[position]
        holder.itemView.tvBreed.text = currentItem
        holder.itemView.setOnClickListener { listener(currentItem) }
    }

    fun setData(breedList: List<String>){
        usedList = breedList
        initialList = breedList
        notifyDataSetChanged()
    }

     fun filter(text: String) {
        if (text.isEmpty()) {
            usedList = initialList
        } else {
            val result = mutableListOf<String>()
            for (item in initialList) {
                //match by name or phone
                if (item.toLowerCase().contains(text.toLowerCase())
                ) {
                    result.add(item)
                }
            }
            usedList = result
        }
        notifyDataSetChanged()
    }


}