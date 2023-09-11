package com.pierretest.cardstoreapp.data.models



import com.google.gson.annotations.SerializedName

data class SingleCardModel(
    @SerializedName("data")
    val `data`: List<DataModel?>? = listOf()
)