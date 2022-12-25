package com.furkanbostan.depoyonetim.API

import com.furkanbostan.depoyonetim.API.Interface.*
import retrofit2.create

class ApiUtils {
    companion object{
        val BASE_URL= "https://api.zykhanofficial.com/" //192.168.56.1 //

        fun getProductDaoInterface():ProductDaoInterface{
            return RetrofitClient.getClient(BASE_URL).create(ProductDaoInterface::class.java)
        }

        fun getCategoryDaoInterface():CategoryDaoInterface{
            return RetrofitClient.getClient(BASE_URL).create(CategoryDaoInterface::class.java)
        }
        fun setCityDaoInterface():CityDaoInterface{
            return RetrofitClient.getClient(BASE_URL).create(CityDaoInterface::class.java)
        }

        fun getSaleDaoInterface():SalesDaoInterface{
            return RetrofitClient.getClient(BASE_URL).create(SalesDaoInterface::class.java)
        }
        fun getPurchasesDaoInterface():PurchaseDaoInterface{
            return RetrofitClient.getClient(BASE_URL).create(PurchaseDaoInterface::class.java)
        }
        fun getStoreDaoInterface():StoreDaoInterface{
            return RetrofitClient.getClient(BASE_URL).create(StoreDaoInterface::class.java)
        }
        fun getWalleteDaoInterface():WalletDaoInterface{
            return RetrofitClient.getClient(BASE_URL).create(WalletDaoInterface::class.java)
        }
        fun getPhotosDaoInterface():PhotosDaoInterface{
            return  RetrofitClient.getClient(BASE_URL).create(PhotosDaoInterface::class.java)
        }
    }
}