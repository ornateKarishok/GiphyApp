package com.mycompany.giphyapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mycompany.giphyapp.databinding.GiphItemBinding
import com.mycompany.giphyapp.models.DataObject

class GiphyListAdapter(val context : Context) : RecyclerView.Adapter<MainViewHolder>() {
    var giphs = mutableListOf<DataObject>()
    fun setGiphList(companies: List<DataObject>) {
        this.giphs = companies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = GiphItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val giph = giphs[position]
        Glide.with(context).load(giph.images.ogImages.url)
            .into(holder.binding.imageViewGiph)

    }

    override fun getItemCount(): Int {
        return giphs.size
    }

}

class MainViewHolder(val binding: GiphItemBinding) : RecyclerView.ViewHolder(binding.root) {
}

