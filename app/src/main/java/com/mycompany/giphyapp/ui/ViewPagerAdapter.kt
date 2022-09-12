package com.mycompany.giphyapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.mycompany.giphyapp.R
import com.mycompany.giphyapp.batabase.entities.Image


class ViewPagerAdapter(private val mContext: Context, private val itemList: ArrayList<Image>) :
    PagerAdapter() {
    private var layoutInflater: LayoutInflater? = null
    private var position: Int = 0

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(mContext)
        val view = layoutInflater!!.inflate(R.layout.full_screen_item, container, false)
        val imageView: ImageView = view.findViewById(R.id.image)
        this.position = position
        Glide.with(mContext).load(itemList[position].url).into(imageView)
        container.addView(view, 0)
        return view
    }

    override fun getCount(): Int {
        return itemList.size
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj
    }
}



