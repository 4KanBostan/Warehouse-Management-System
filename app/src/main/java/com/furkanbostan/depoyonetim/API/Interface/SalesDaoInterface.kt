package com.furkanbostan.depoyonetim.API.Interface

import com.furkanbostan.depoyonetim.MyModel.SaleModel
import com.furkanbostan.depoyonetim.ViewModel.SaleViewModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SalesDaoInterface {

    @POST("api/sales")
    fun addSale(@Body saleModel: SaleModel): Call<Void>

    @GET("api/sales")
    fun getAllSale():Call<List<SaleViewModel>>
}