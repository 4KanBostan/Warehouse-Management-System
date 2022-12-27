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
import com.furkanbostan.depoyonetim.Store.StoreFragmentDirections
import com.squareup.picasso.Picasso

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
        var productCategory : TextView
        var productPurchase : TextView
        var productSale: TextView
        var prdouctDetails:ImageView
        var productImage:ImageView


        init {
            productName = view.findViewById(R.id.tv_sale_name)
            productAdet =view.findViewById(R.id.product_adet)
            productDescription = view.findViewById(R.id.tv_product_details)
            productCategory = view.findViewById(R.id.tv_product_category)
            productPurchase = view.findViewById(R.id.tv_product_purchase)
            productSale = view.findViewById(R.id.tv_product_sale)
            prdouctDetails = view.findViewById(R.id.tv_product_go_details)
            productImage = view.findViewById(R.id.stat_image_photo)

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
        holder.productCategory.text = product.category_name.toString()
        holder.productSale.text = product.sale_price.toString()
        Picasso.get().load(product.image).into(holder.productImage)
        holder.prdouctDetails.setOnClickListener{
            val action = StoreFragmentDirections.actionStoreFragmentToProductDetailsFragment(product.id)
            Navigation.findNavController(it).navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return  productModelList.size

    }
}