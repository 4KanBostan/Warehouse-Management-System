package com.furkanbostan.depoyonetim.API.Interface

import com.furkanbostan.depoyonetim.MyModel.PhotoModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface PhotosDaoInterface {
    @POST("api/photos")
    fun addPhoto(@Body photoModel: PhotoModel):Call<Void>

}