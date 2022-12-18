package com.furkanbostan.depoyonetim.API.Interface

import androidx.annotation.Nullable
import com.furkanbostan.depoyonetim.ViewModel.CityViewModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface CityDaoInterface {


    @GET("api/cities")
    fun sehirCagir():Call<List<CityViewModel>>
}