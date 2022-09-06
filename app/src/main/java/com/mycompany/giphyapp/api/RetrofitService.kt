package com.mycompany.giphyapp.api

import com.mycompany.giphyapp.models.DataResult
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {
    @GET("gifs/trending?api_key=yT5HFZEDVdvNrOZcJHnr2dfUaJU2dQ5R")
    fun getGifs(): Call<DataResult>

    companion object {
        var retrofitService: RetrofitService? = null

        fun getInstance() : RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.giphy.com/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}