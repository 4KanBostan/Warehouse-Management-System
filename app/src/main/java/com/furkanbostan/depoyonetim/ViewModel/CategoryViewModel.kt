package com.furkanbostan.depoyonetim.ViewModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CategoryViewModel(@SerializedName("id")
                             @Expose
                             val Id: Int,
                             @SerializedName ("storeId")
                             @Expose
                             val StoreId: Int,
                             @SerializedName ("name")
                             @Expose
                             val Name:String,
                             @SerializedName ("store")
                             @Expose
                             val Store: StoreViewModel,
                             @SerializedName ("products")
                             @Expose
                             val Products: List<ProductViewModel>) {
}