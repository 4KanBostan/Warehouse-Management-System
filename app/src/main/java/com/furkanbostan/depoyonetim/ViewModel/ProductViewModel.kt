package com.furkanbostan.depoyonetim.ViewModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
//https://localhost:7240/api/Cities
//https://localhost:7240/api/Categories
data class ProductViewModel(@SerializedName("id")
                            @Expose
                            val Id:Int,
                            @SerializedName("categoryId")
                            @Expose
                            val CategoryId:Int,
                            @SerializedName("name")
                            @Expose
                            val Name: String,
                            @SerializedName("description")
                            @Expose
                            val Description:String,
                            @SerializedName("price")
                            @Expose
                            val Price:Float,
                            @SerializedName("category")
                            @Expose
                            val Category:CategoryViewModel,
                            @SerializedName("photo")
                            @Expose
                            val Photo:PhotoViewModel,
                            @SerializedName("purchases")
                            @Expose
                            val Purchases: List<PurchasesViewModel>,
                            @SerializedName("sales")
                            @Expose
                            val Sales: List<SaleViewModel>,
                            @SerializedName("inventories")
                            @Expose
                            val Inventories : List<InventoryViewModel>) {
}
