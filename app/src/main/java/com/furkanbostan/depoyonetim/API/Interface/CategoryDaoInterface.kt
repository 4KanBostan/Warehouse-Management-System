package com.furkanbostan.depoyonetim.API.Interface

import com.furkanbostan.depoyonetim.Model.CategoryModel
import com.furkanbostan.depoyonetim.ViewModel.CategoryViewModel
import com.furkanbostan.depoyonetim.ViewModel.CityViewModel
import retrofit2.Call
import retrofit2.http.*

interface CategoryDaoInterface {

    @POST("api/categories/{storeId}")
    fun kategoriEkle(@Path("storeId") storeId:Int, @Body categoryModel: CategoryModel):Call<Void>

    @GET("api/categories")
    fun getAllCAtegory():Call<List<CategoryViewModel>>


}