package com.mycompany.giphyapp.models

import com.google.gson.annotations.SerializedName

data class DataObject(
    @SerializedName("images") val images: DataImage
)
