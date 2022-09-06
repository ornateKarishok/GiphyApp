package com.mycompany.giphyapp.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GiphyViewModelFactory constructor(private val repository: GiphyRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(GiphyViewModel::class.java)) {
            GiphyViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}