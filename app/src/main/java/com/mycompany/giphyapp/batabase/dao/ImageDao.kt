package com.mycompany.giphyapp.batabase.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.mycompany.giphyapp.batabase.entities.Image

@Dao
interface ImageDao {
    @Insert(onConflict = IGNORE)
    suspend fun addImage(image: Image)

    @Delete
    suspend fun deleteGif(gif: Image)

    @Query("SELECT * FROM IMAGES")
    fun readAllImages() : LiveData<List<Image>>
}