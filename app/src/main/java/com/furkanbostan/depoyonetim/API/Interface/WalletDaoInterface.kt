package com.furkanbostan.depoyonetim.API.Interface

import com.furkanbostan.depoyonetim.MyModel.IncomeWalletModel
import com.furkanbostan.depoyonetim.ViewModel.WalletViewModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path


interface WalletDaoInterface {

    @GET("api/wallets")
    fun getWallets(): Call<List<WalletViewModel>>
    @PATCH("api/wallets/{id}")
    fun updateIncome(@Path("id")walletID: Int,@Body incomeWalletModel: IncomeWalletModel):Call<Void>
}