package com.mycompany.giphyapp.api

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mycompany.giphyapp.models.DataResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GiphyViewModel constructor(private val repository: GiphyRepository) : ViewModel() {
    val giphyList: MutableLiveData<DataResult> = MutableLiveData()
    val errorMessage = MutableLiveData<String>()

    fun getGiphyList() {
            val response = repository.getGifs()
            response.enqueue(object : Callback<DataResult> {
                override fun onResponse(
                    call: Call<DataResult?>,
                    response: Response<DataResult>
                ) {
                    giphyList.postValue(response.body())
                }

                override fun onFailure(call: Call<DataResult>, t: Throwable) {
                    errorMessage.postValue(t.message)
                }
            })

    }
}

