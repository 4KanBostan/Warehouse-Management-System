package com.furkanbostan.depoyonetim.API.Interface

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface CategoryDaoInterface {

    @POST("categories")
    @FormUrlEncoded
    fun kategoriEkle(@Field("storeId") storeId: Int,
                     @Field("name") categoryName: String)
}