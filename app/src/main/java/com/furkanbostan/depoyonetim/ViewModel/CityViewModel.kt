package com.furkanbostan.depoyonetim.ViewModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CityViewModel(@SerializedName("id")
                         @Expose
                         val Id:Int,
                         @SerializedName("name")
                         @Expose
                         val Name :String,
                         @SerializedName("stores")
                         @Expose
                         val Stores: List<StoreViewModel>) {
}