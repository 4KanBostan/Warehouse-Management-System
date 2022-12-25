package com.furkanbostan.depoyonetim.Home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.furkanbostan.depoyonetim.API.ApiUtils
import com.furkanbostan.depoyonetim.MyModel.StoreModel
import com.furkanbostan.depoyonetim.R
import com.furkanbostan.depoyonetim.ViewModel.CityViewModel
import com.furkanbostan.depoyonetim.ViewModel.StoreViewModel
import com.furkanbostan.depoyonetim.ViewModel.WalletViewModel
import com.furkanbostan.depoyonetim.databinding.FragmentEditProfilBinding
import com.furkanbostan.depoyonetim.databinding.FragmentEditStoreBinding
import com.furkanbostan.depoyonetim.databinding.FragmentStoreBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EditStoreFragment : Fragment() {
   private var binding:FragmentEditStoreBinding?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= FragmentEditStoreBinding.inflate(layoutInflater,container,false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.backHomeButton.setOnClickListener{
            findNavController().popBackStack()
        }

        getStore()

        binding!!.btnUpdate.setOnClickListener{

        }
    }



    fun getStore(){

        val sdi = ApiUtils.getStoreDaoInterface()
        val getStore = sdi.getStore()

        getStore.enqueue(object : Callback<List<StoreViewModel>>{
            override fun onResponse(call: Call<List<StoreViewModel>>, response: Response<List<StoreViewModel>>) {
                val arrayStoreTemp = response.body()
                val store = arrayStoreTemp!![0]
                getCity(store.Id)
                binding!!.etStoreNAme.setText(store.Name)
                binding!!.etStorePhone.setText(store.Phone)
                binding!!.etStoreAddress.setText(store.Address)
                binding!!.etStoreBalance.setText(store.Wallets[0].Balance.toString())

            }

            override fun onFailure(call: Call<List<StoreViewModel>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun getCity(storeId:Int){
        val cdi = ApiUtils.setCityDaoInterface()
        val getCity = cdi.sehirCagir()
        var categoryName=""
        getCity.enqueue(object :Callback<List<CityViewModel>>{
            override fun onResponse(call: Call<List<CityViewModel>>, response: Response<List<CityViewModel>>) {
                val cityListTemp = response.body()

                for (k in cityListTemp!!){
                    if (k.Id==storeId){
                        categoryName=k.Name
                    }
                }
                binding!!.etStoreCity.setText(categoryName)


            }

            override fun onFailure(call: Call<List<CityViewModel>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun getWallet(){
        val wdi= ApiUtils.getWalleteDaoInterface()
        val getWallet = wdi.getWallets()

        getWallet.enqueue(object :Callback<List<WalletViewModel>>{
            override fun onResponse(
                call: Call<List<WalletViewModel>>,
                response: Response<List<WalletViewModel>>
            ) {

            }

            override fun onFailure(call: Call<List<WalletViewModel>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }


    fun updteStore(storeId: Int, storeModel: StoreModel){
        val sdi = ApiUtils.getStoreDaoInterface()
        val updateStore = sdi.updateStore(storeId,storeModel)

        updateStore.enqueue(object : Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
    fun getStoreForUpdate(){

        val sdi = ApiUtils.getStoreDaoInterface()
        val getStore = sdi.getStore()

        getStore.enqueue(object : Callback<List<StoreViewModel>>{
            override fun onResponse(call: Call<List<StoreViewModel>>, response: Response<List<StoreViewModel>>) {
                val arrayStoreTemp = response.body()
                val store = arrayStoreTemp!![0]
            }

            override fun onFailure(call: Call<List<StoreViewModel>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun getCityForUpdate(storeId:Int){
        val cdi = ApiUtils.setCityDaoInterface()
        val getCity = cdi.sehirCagir()
        var categoryName=""
        getCity.enqueue(object :Callback<List<CityViewModel>>{
            override fun onResponse(call: Call<List<CityViewModel>>, response: Response<List<CityViewModel>>) {
                val cityListTemp = response.body()

                for (k in cityListTemp!!){
                    if (k.Id==storeId){
                        categoryName=k.Name
                    }
                }
                binding!!.etStoreCity.setText(categoryName)


            }

            override fun onFailure(call: Call<List<CityViewModel>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}