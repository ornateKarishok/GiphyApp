package com.mycompany.giphyapp.models

import com.google.gson.annotations.SerializedName

data class DataImage(
    @SerializedName("original") val ogImages: OgImage
)
