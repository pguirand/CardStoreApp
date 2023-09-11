package com.pierretest.cardstoreapp.data.models


import com.google.gson.annotations.SerializedName

data class CardImageModel(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("image_url")
    val imageUrl: String? = "",
    @SerializedName("image_url_cropped")
    val imageUrlCropped: String? = "",
    @SerializedName("image_url_small")
    val imageUrlSmall: String? = ""
)