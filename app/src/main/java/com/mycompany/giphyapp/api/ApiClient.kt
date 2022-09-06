package com.mycompany.giphyapp.api

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private const val BASE_URL = "https://api.giphy.com/v1/"

//    var retrofitService: RetrofitService? = null
//
//    fun getInstance(): RetrofitService {
//        if (retrofitService == null) {
//            val retrofit = Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//            retrofitService = retrofit.create(RetrofitService::class.java)
//        }
//        return retrofitService!!
//
//
//    }
}