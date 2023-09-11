package com.pierretest.cardstoreapp.data.models



import com.google.gson.annotations.SerializedName

data class CardPriceModel(
    @SerializedName("amazon_price")
    val amazonPrice: String? = "",
    @SerializedName("cardmarket_price")
    val cardmarketPrice: String? = "",
    @SerializedName("coolstuffinc_price")
    val coolstuffincPrice: String? = "",
    @SerializedName("ebay_price")
    val ebayPrice: String? = "",
    @SerializedName("tcgplayer_price")
    val tcgplayerPrice: String? = ""
)