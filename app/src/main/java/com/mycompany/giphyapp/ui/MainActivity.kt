package com.mycompany.giphyapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mycompany.giphyapp.R
import com.mycompany.giphyapp.util.FragmentUtil

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FragmentUtil.replaceFragment(GiphyListFragment.newInstance(), supportFragmentManager)
    }
}