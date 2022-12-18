package com.furkanbostan.depoyonetim.API

import com.furkanbostan.depoyonetim.ViewModel.CityViewModel
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface DepoApiServis {

    @GET(":7240/api/cities")//burada var birlesiyorlar bak normalde şöyle olmalı knaka api hepsinde var diye
  suspend  fun sehirCagir():List<CityViewModel>
}