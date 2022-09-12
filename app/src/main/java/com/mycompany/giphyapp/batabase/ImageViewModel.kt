package com.mycompany.giphyapp.batabase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.mycompany.giphyapp.batabase.entities.Image
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImageViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<Image>>
    private val repository : ImageRepository

    init {
        val imageDao = ImageDatabase.getDatabase(application).imageDao()
        repository = ImageRepository(imageDao)
        readAllData = repository.readAllData
    }

    fun addImage(image: Image){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addImage(image)
        }
    }
    fun deleteGif(gif: Image){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteImage(gif)
        }
    }
}