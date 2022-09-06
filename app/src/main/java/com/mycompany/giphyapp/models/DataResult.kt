package com.mycompany.giphyapp.models

import com.google.gson.annotations.SerializedName

data class DataResult(
    @SerializedName("data") val res: List<DataObject>
)
