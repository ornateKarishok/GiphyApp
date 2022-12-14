package com.mycompany.giphyapp.batabase

import androidx.lifecycle.LiveData
import com.mycompany.giphyapp.batabase.dao.ImageDao
import com.mycompany.giphyapp.batabase.entities.Image
import kotlinx.coroutines.flow.Flow

class ImageRepository(private val imageDao: ImageDao) {

    val readAllData: LiveData<List<Image>> = imageDao.readAllImages()

    suspend fun addImage(image: Image){
        imageDao.addImage(image)
    }

    suspend fun deleteImage(gif: Image){
        imageDao.deleteGif(gif)
    }
     fun searchDatabase(searchQuery: String) : Flow<List<Image>>{
        return imageDao.getImageByName(searchQuery)
    }
}