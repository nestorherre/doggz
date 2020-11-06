package com.nhapps.doggz.view.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nhapps.doggz.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.rv_breed_images_item.view.*

class BreedImagesRVAdapter(): RecyclerView.Adapter<BreedImagesRVAdapter.MyViewHolder>() {

    private var myList = emptyList<String>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_breed_images_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = myList[position]
        Picasso.get().load(currentItem).into(holder.itemView.ivDog)
        val breedType = currentItem.substringBeforeLast("/").substringAfterLast("/")

        holder.itemView.tvBreedImage.text = breedType
    }

    fun setData(breedImages: List<String>){
        myList = breedImages
        notifyDataSetChanged()
    }


}