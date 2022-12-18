package com.furkanbostan.depoyonetim.ViewModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SaleViewModel(@SerializedName("Id")
                    @Expose
                    val Id : Int,
                    @SerializedName("productId")
                    @Expose
                    val ProductId:Int,
                    @SerializedName("quantity")
                    @Expose
                    val Quantity: Int,
                    @SerializedName("product")
                    @Expose
                    val Product: ProductViewModel) {

}
