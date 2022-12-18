package com.furkanbostan.depoyonetim.API

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

private const val BASE_URL = "https://127.0.0.1:7240" //aynen burada base url ile api isteği birleştirilince nasıl bir url oluşuyor ona bakmamız lazım

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()
// sorun bizde kanki biraz daha baksın choreme da çalıstı cünkü onu demeye çalışıyordumbak ama şurada slash yok
object DepoApi {
    val retrofitService:DepoApiServis by lazy { retrofit.create(DepoApiServis::class.java)}
}