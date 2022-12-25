package com.furkanbostan.depoyonetim.API.Interface

import com.furkanbostan.depoyonetim.MyModel.PurchasesModel
import com.furkanbostan.depoyonetim.Status.StatsPurchaseModel
import com.furkanbostan.depoyonetim.ViewModel.PurchasesViewModel
import retrofit2.Call
import retrofit2.http.*

interface PurchaseDaoInterface {
    @POST("api/purchases")
    fun addPurchase(@Body purchasesModel: PurchasesModel):Call<Void>

    @GET("api/purchases")
    fun gelAllpurchase():Call<List<PurchasesViewModel>>

    @PATCH("api/purchases/{id}")
    fun updatePurchase(@Path("id")purcId:Int, @Body purchasesModel: PurchasesModel):Call<Void>
}