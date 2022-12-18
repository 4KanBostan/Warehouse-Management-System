package com.furkanbostan.depoyonetim.API

import com.furkanbostan.depoyonetim.API.Interface.CategoryDaoInterface
import com.furkanbostan.depoyonetim.API.Interface.CityDaoInterface
import com.furkanbostan.depoyonetim.API.Interface.ProductDaoInterface

class ApiUtils {
    companion object{
        val BASE_URL= "https://10.58.12.115:7240/" //192.168.56.1 //

        fun getProductDaoInterface():ProductDaoInterface{
            return RetrofitClient.getClient(BASE_URL).create(ProductDaoInterface::class.java)
        }

        fun getCategoryDaoInterface():CategoryDaoInterface{
            return RetrofitClient.getClient(BASE_URL).create(CategoryDaoInterface::class.java)
        }
        fun setCityDaoInterface():CityDaoInterface{
            return RetrofitClient.getClient(BASE_URL).create(CityDaoInterface::class.java)
        }

    }
}