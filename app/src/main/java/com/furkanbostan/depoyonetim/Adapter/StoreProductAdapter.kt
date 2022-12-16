package com.furkanbostan.depoyonetim.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation

import androidx.recyclerview.widget.RecyclerView
import com.furkanbostan.depoyonetim.Model.StoreProductModel
import com.furkanbostan.depoyonetim.R

class StoreProductAdapter (private val mcontext :Context,
                           private var productModelList :ArrayList<StoreProductModel>): RecyclerView.Adapter<StoreProductAdapter.StoreProductHolder>(){

    fun setFilteredList(filteredList: ArrayList<StoreProductModel>) {
        this.productModelList = filteredList
        notifyDataSetChanged()
    }
    inner class StoreProductHolder (view : View):RecyclerView.ViewHolder(view){
        var productName : TextView
        var productAdet : TextView
        var productDescription : TextView
        var productPurchase : TextView
        var productSales : TextView
        var prdouctDetails:ImageView


        init {
            productName = view.findViewById(R.id.tv_product_name)
            productAdet =view.findViewById(R.id.product_adet)
            productDescription = view.findViewById(R.id.tv_product_details)
            productPurchase = view.findViewById(R.id.tv_product_satis)
            productSales = view.findViewById(R.id.tv_product_alis)
            prdouctDetails = view.findViewById(R.id.tv_product_go_details)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreProductHolder {
        val tasarim = LayoutInflater.from(mcontext).inflate(R.layout.item_product_store,parent,false)
        return StoreProductHolder(tasarim)
    }

    override fun onBindViewHolder(holder: StoreProductHolder, position: Int) {
        val product = productModelList[position]
        holder.productName.text = product.name
        holder.productAdet.text = product.adet.toString()
        holder.productDescription.text = product.description
        holder.productPurchase.text = product.purchase_price.toString()
        holder.productSales.text = product.sales_price.toString()
        holder.prdouctDetails.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_storeFragment_to_productDetailsFragment)
        }

    }

    override fun getItemCount(): Int {
        return  productModelList.size

    }
}