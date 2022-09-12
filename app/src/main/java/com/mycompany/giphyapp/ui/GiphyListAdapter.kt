package com.mycompany.giphyapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ExpandableListView
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mycompany.giphyapp.R
import com.mycompany.giphyapp.batabase.entities.Image
import com.mycompany.giphyapp.databinding.GiphItemBinding
import com.mycompany.giphyapp.models.DataObject

class GiphyListAdapter(val context: Context) :
    RecyclerView.Adapter<GiphyListAdapter.MainViewHolder>() {
    lateinit var clickListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnClickListener(listener: OnItemClickListener) {
        clickListener = listener
    }

    var giphs = emptyList<Image>()

    fun setData(image: List<Image>) {
        this.giphs = image
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.giph_item, parent, false),
            clickListener
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val giph = giphs[position]
        Glide.with(context).load(giph.url)
            .into(holder.imageView)

    }

    override fun getItemCount(): Int {
        return giphs.size
    }

    class MainViewHolder(itemView: View, listener: OnItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.imageViewGiph)

        init {
            itemView.setOnClickListener {

                listener.onItemClick(adapterPosition)
            }

        }
    }
}



