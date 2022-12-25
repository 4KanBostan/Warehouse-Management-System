package com.furkanbostan.depoyonetim.Store

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.furkanbostan.depoyonetim.API.ApiUtils
import com.furkanbostan.depoyonetim.MyModel.PurchasesModel
import com.furkanbostan.depoyonetim.R
import com.furkanbostan.depoyonetim.databinding.FragmentProductDetailsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductDetailsFragment : Fragment() {

    private lateinit var binding:FragmentProductDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProductDetailsBinding.inflate(layoutInflater,container,false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backToStore.setOnClickListener{
            findNavController().popBackStack()
        }

        binding.btnDetailsPurchase.setOnClickListener{

        }
    }


   /* fun updatePurchase(purchasesModel: PurchasesModel){
        val sdi = ApiUtils.getPurchasesDaoInterface()
        var addPurchase = sdi.updatePurchase(purchasesModel)
        addPurchase.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Log.e("addPurchase","Başarılı")
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("addPurchase",t.toString())
            }

        })
    }
*/


}