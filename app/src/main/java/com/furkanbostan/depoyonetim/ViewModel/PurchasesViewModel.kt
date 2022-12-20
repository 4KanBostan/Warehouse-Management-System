package com.furkanbostan.depoyonetim.ViewModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PurchasesViewModel(@SerializedName("id")
                              @Expose
                              val Id: Int,
                              @SerializedName("productId")
                              @Expose
                              val ProductId: Int,
                              @SerializedName("quantity")
                              @Expose
                              val Quantity: Int,
                              @SerializedName("price")
                              @Expose
                              val Price:Float,
                              @SerializedName("product")
                              @Expose
                              val Product: ProductViewModel) {

}
