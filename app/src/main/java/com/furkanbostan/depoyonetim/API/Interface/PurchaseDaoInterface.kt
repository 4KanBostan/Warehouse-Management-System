package com.furkanbostan.depoyonetim.API.Interface

import com.furkanbostan.depoyonetim.MyModel.PurchasesModel
import com.furkanbostan.depoyonetim.ViewModel.PurchasesViewModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PurchaseDaoInterface {
    @POST("api/purchases")
    fun addPurchase(@Body purchasesModel: PurchasesModel):Call<Void>

    @GET("api/purchases")
    fun gelAllpurchase():Call<List<PurchasesViewModel>>
}