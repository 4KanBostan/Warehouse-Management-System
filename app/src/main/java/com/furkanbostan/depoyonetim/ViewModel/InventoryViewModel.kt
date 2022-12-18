package com.furkanbostan.depoyonetim.ViewModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class InventoryViewModel(@SerializedName("id")
                              @Expose
                              val Id :Int,
                              @SerializedName("productId")
                              @Expose
                              val ProductId: Int,
                              @SerializedName("storeId")
                              @Expose
                              val StoreId:Int,
                              @SerializedName("quantity")
                              @Expose
                              val Quantity: Int,
                              @SerializedName("store")
                              @Expose
                              val Store:StoreViewModel,
                              @SerializedName("product")
                              @Expose
                              val Product:ProductViewModel) {
}