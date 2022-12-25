package com.furkanbostan.depoyonetim.API.Interface

import com.furkanbostan.depoyonetim.MyModel.StoreModel
import com.furkanbostan.depoyonetim.ViewModel.StoreViewModel
import retrofit2.Call
import retrofit2.http.*

interface StoreDaoInterface {
    @GET("api/stores")
    fun getStore():Call<List<StoreViewModel>>
    @PATCH("api/stores/{id}")
    fun updateStore(@Path("id") storeId:Int, @Body storeModel: StoreModel):Call<Void>
}