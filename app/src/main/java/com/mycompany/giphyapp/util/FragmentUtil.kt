package com.mycompany.giphyapp.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.mycompany.giphyapp.R

class FragmentUtil {
    companion object ReplaceFragment {
        fun replaceFragment(fragment: Fragment, fragmentManager: FragmentManager) {
            val t: FragmentTransaction = fragmentManager.beginTransaction()
            t.replace(R.id.container, fragment).commit()
        }
    }
}