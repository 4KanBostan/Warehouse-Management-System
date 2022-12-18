package com.furkanbostan.depoyonetim.ViewModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PhotoViewModel(
    @SerializedName("id")
    @Expose
    val Id :Int,
    @SerializedName("productId")
    @Expose
    val ProductId: Int,
    @SerializedName("path")
    @Expose
    val Path: String,
    @SerializedName("product")
    @Expose
    val Product: ProductViewModel) {
}