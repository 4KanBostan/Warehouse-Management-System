package com.furkanbostan.depoyonetim.ViewModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WalletViewModel(@SerializedName ("id")
                           @Expose
                           var Id:Int,
                           @SerializedName ("storeId")
                           @Expose
                           var StoreId:Int,
                           @SerializedName ("income")
                           @Expose
                           var Income:Float,
                           @SerializedName ("outGoing")
                           @Expose
                           var OutGoing:Float,
                           @SerializedName ("balance")
                           @Expose
                           var Balance: Float,
                           @SerializedName ("store")
                           @Expose
                           var Store:StoreViewModel) {

}
