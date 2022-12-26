package com.furkanbostan.depoyonetim.Home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.furkanbostan.depoyonetim.API.ApiUtils
import com.furkanbostan.depoyonetim.MyModel.IncomeWalletModel
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
   private lateinit var binding:FragmentEditStoreBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= FragmentEditStoreBinding.inflate(layoutInflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backHomeButton.setOnClickListener{
            findNavController().popBackStack()
        }

        getStore()

        binding.btnUpdate.setOnClickListener{
            var cityName= binding.etStoreNAme.text.toString()
            getCityIdForUpdate(cityName)
        }
        binding.btnAddWallet.setOnClickListener{
            var addBalance = binding.etBakiyeEkle.text.toString().toFloat()

           // updateWallet(1,)
        }
    }



    fun getStore(){

        val sdi = ApiUtils.getStoreDaoInterface()
        val getStore = sdi.getStore()

        getStore.enqueue(object : Callback<List<StoreViewModel>>{
            override fun onResponse(call: Call<List<StoreViewModel>>, response: Response<List<StoreViewModel>>) {
                val arrayStoreTemp = response.body()
                val store = arrayStoreTemp!![0]
                getCityName(store.CityId)
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

    fun getCityName(storeId:Int){
        val cdi = ApiUtils.setCityDaoInterface()
        val getCity = cdi.sehirCagir()
        var cityName=""
        getCity.enqueue(object :Callback<List<CityViewModel>>{
            override fun onResponse(call: Call<List<CityViewModel>>, response: Response<List<CityViewModel>>) {
                val cityListTemp = response.body()

                for (k in cityListTemp!!){
                    if (k.Id==storeId){
                        cityName=k.Name
                    }
                }
                binding!!.etStoreCity.setText(cityName)


            }

            override fun onFailure(call: Call<List<CityViewModel>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun updateWallet(walletId:Int, incomeWalletModel: IncomeWalletModel) {
        val wdi = ApiUtils.getWalleteDaoInterface()
        val updateWallet = wdi.updateIncome(walletId, incomeWalletModel)
        updateWallet.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Toast.makeText(context, "Bakiye eklendi", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }


    fun updteStore(storeId: Int, storeModel: StoreModel){
        val sdi = ApiUtils.getStoreDaoInterface()
        val updateStore = sdi.updateStore(storeId,storeModel)
        updateStore.enqueue(object : Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Toast.makeText(context,"Mağaza bilgileri güncellendi",Toast.LENGTH_SHORT).show()
                getStore()
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

    fun getCityIdForUpdate(cityName:String){
        val cdi = ApiUtils.setCityDaoInterface()
        val getCity = cdi.sehirCagir()
        var cityId : Int? =null
        getCity.enqueue(object :Callback<List<CityViewModel>>{
            override fun onResponse(call: Call<List<CityViewModel>>, response: Response<List<CityViewModel>>) {
                val cityListTemp = response.body()

                for (k in cityListTemp!!){
                    if (k.Name.trim().toLowerCase()==cityName.trim().toLowerCase()){
                        cityId=k.Id
                        var cityAddress = binding.etStoreAddress.text.toString()
                        var cityDescription = ""
                        var cityPhone= binding.etStorePhone.text.toString()
                        var storeModel = StoreModel(cityId!!, cityName,cityAddress,cityDescription,cityPhone)
                        updteStore(1, storeModel)
                    }
                }
            }

            override fun onFailure(call: Call<List<CityViewModel>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}