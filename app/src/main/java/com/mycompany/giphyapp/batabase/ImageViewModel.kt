package com.mycompany.giphyapp.batabase

import android.app.Application
import androidx.lifecycle.*
import com.mycompany.giphyapp.batabase.entities.Image
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImageViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<Image>>
    private val repository: ImageRepository

    init {
        val imageDao = ImageDatabase.getDatabase(application).imageDao()
        repository = ImageRepository(imageDao)
        readAllData = repository.readAllData
    }

    fun addImage(image: Image) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addImage(image)
        }
    }

    fun deleteGif(gif: Image) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteImage(gif)
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<Image>> {
        return repository.searchDatabase(searchQuery).asLiveData()
    }
}
