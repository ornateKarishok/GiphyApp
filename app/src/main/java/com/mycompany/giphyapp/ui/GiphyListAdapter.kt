package com.mycompany.giphyapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mycompany.giphyapp.R
import com.mycompany.giphyapp.batabase.entities.Image

class GiphyListAdapter(private val context: Context) :
    RecyclerView.Adapter<GiphyListAdapter.MainViewHolder>() {
    private lateinit var clickListener: OnItemClickListener
    private var gifs = emptyList<Image>()

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnClickListener(listener: OnItemClickListener) {
        clickListener = listener
    }

    fun setData(image: List<Image>) {
        this.gifs = image
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.giph_item, parent, false),
            clickListener
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val gif = gifs[position]
        Glide.with(context).load(gif.url)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return gifs.size
    }

    class MainViewHolder(itemView: View, listener: OnItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.imageViewGiph)!!

        init {
            itemView.setOnClickListener {

                listener.onItemClick(adapterPosition)
            }

        }
    }
}



