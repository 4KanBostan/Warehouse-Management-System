package com.furkanbostan.depoyonetim.ViewModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class StoreViewModel(@SerializedName("id")
                          @Expose
                          val Id: Int,
                          @SerializedName("cityId")
                          @Expose
                          val CityId: Int,
                          @SerializedName("name")
                          @Expose
                          val Name:String,
                          @SerializedName("address")
                          @Expose
                          val Address: String,
                          @SerializedName("description")
                          @Expose
                          val Description: String,
                          @SerializedName("phone")
                          @Expose
                            val Phone:String,
                          @SerializedName("categories")
                          @Expose
                          val Categories:List<CategoryViewModel>,
                          @SerializedName("wallets")
                          @Expose
                          val Wallets: List<WalletViewModel>,
                          @SerializedName("inventories")
                          @Expose
                          val Inventories: List<InventoryViewModel>) {

}
