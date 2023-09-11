package com.pierretest.cardstoreapp.data.models



import com.google.gson.annotations.SerializedName

data class DataModel(
    @SerializedName("card_images")
    val cardImages: List<CardImageModel?>? = listOf(),
    @SerializedName("card_prices")
    val cardPrices: List<CardPriceModel?>? = listOf(),
    @SerializedName("card_sets")
    val cardSets: List<CardSetModel?>? = listOf(),
    @SerializedName("desc")
    val desc: String? = "",
    @SerializedName("frameType")
    val frameType: String? = "",
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("race")
    val race: String? = "",
    @SerializedName("type")
    val type: String? = ""
)