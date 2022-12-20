package com.furkanbostan.depoyonetim.API.Interface

import com.furkanbostan.depoyonetim.MyModel.CityModel
import com.furkanbostan.depoyonetim.ViewModel.CityViewModel
import retrofit2.Call
import retrofit2.http.*

interface CityDaoInterface {


    @GET("api/cities")
    fun sehirCagir():Call<List<CityViewModel>>

    @POST("api/cities/")
    fun sehirEKle(@Body cityModel: CityModel):Call<Void>



    @DELETE("api/Cities/{id}")
    fun sehirSil(@Path("id") cityId :Int):Call<Void>
}