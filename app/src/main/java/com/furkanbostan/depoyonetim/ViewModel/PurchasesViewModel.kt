package com.furkanbostan.depoyonetim.ViewModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PurchasesViewModel(@SerializedName("Id")
                              @Expose
                              val Id: Int,
                              @SerializedName("ProductId")
                              @Expose
                              val ProductId: Int,
                              @SerializedName("Quantity")
                              @Expose
                              val Quantity: Int,
                              @SerializedName("Price")
                              @Expose
                              val Price:Float,
                              @SerializedName("Product")
                              @Expose
                              val Product: ProductViewModel) {

}
