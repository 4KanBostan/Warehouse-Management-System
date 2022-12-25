package com.furkanbostan.depoyonetim.API.Interface

import com.furkanbostan.depoyonetim.ViewModel.WalletViewModel
import retrofit2.Call
import retrofit2.http.GET


interface WalletDaoInterface {

    @GET("api/wallets")
    fun getWallets(): Call<List<WalletViewModel>>
}