package com.mycompany.giphyapp.api

class GiphyRepository constructor(private val retrofitService: RetrofitService) {
    fun getGifs() = retrofitService.getGifs()
}