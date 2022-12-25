package com.furkanbostan.depoyonetim.Home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.furkanbostan.depoyonetim.API.ApiUtils
import com.furkanbostan.depoyonetim.R
import com.furkanbostan.depoyonetim.ViewModel.CityViewModel
import com.furkanbostan.depoyonetim.ViewModel.StoreViewModel
import com.furkanbostan.depoyonetim.ViewModel.WalletViewModel
import com.furkanbostan.depoyonetim.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        val view = binding!!.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding!!.imageViewProfilSetting.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_editProfilFragment)
        }
        binding!!.imageViewStoreSetting.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_editStoreFragment)
        }

getStore()


    }

    fun getStore(){

        val sdi = ApiUtils.getStoreDaoInterface()
        val getStore = sdi.getStore()

        getStore.enqueue(object : Callback<List<StoreViewModel>> {
            override fun onResponse(call: Call<List<StoreViewModel>>, response: Response<List<StoreViewModel>>) {
                val arrayStoreTemp = response.body()
                Log.e("size",arrayStoreTemp!!.size.toString())
                val store = arrayStoreTemp!!.first()

                binding!!.tvStoreNAme.setText(store.Name)
                binding!!.tvPhone.setText(store.Phone)
                binding!!.tvStoreAddress.setText(store.Address)
                binding!!.tvStoreBalance.setText(store.Wallets[0].Balance.toString())

            }

            override fun onFailure(call: Call<List<StoreViewModel>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}