package com.furkanbostan.depoyonetim.API.Interface

import com.furkanbostan.depoyonetim.MyModel.ProdId
import com.furkanbostan.depoyonetim.Model.ProductModel
import com.furkanbostan.depoyonetim.ViewModel.ProductViewModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductDaoInterface {

    @GET("api/products")
    fun productGetir(): Call<List<ProductViewModel>>
    @GET("api/products")
    fun poductIdGetir():Call<List<ProdId>>

    @POST("api/products")
    fun productEkle(@Body productModel: ProductModel):Call<Void>

}