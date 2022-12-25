package com.furkanbostan.depoyonetim.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.furkanbostan.depoyonetim.Model.CategoryModel
import com.furkanbostan.depoyonetim.R
import com.furkanbostan.depoyonetim.Status.StatsPurchaseModel
import com.squareup.picasso.Picasso

class StatPurchaseAdapter(private val mContext: Context,
                          private var purchaseModelList: ArrayList<StatsPurchaseModel>) :RecyclerView.Adapter<StatPurchaseAdapter.StatPurchaseViewHolder>(){

    fun setNewList(newList: ArrayList<StatsPurchaseModel>) {
        this.purchaseModelList = newList
        notifyDataSetChanged()
    }
    inner class StatPurchaseViewHolder(view:View):RecyclerView.ViewHolder(view){
        var purchaseName:TextView
        var purchasePiece: TextView
        var PurchasePrice: TextView
        var image:ImageView
        init {
            purchaseName = view.findViewById(R.id.tv_purchase_name)
            purchasePiece= view.findViewById(R.id.tv_purchase_piece)
            PurchasePrice = view.findViewById(R.id.tv_purchase_price)
            image = view.findViewById(R.id.stat_image_photo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatPurchaseViewHolder {
        val tasarim = LayoutInflater.from(mContext).inflate(R.layout.item_stat_purchase,parent,false)
        return StatPurchaseViewHolder(tasarim)
    }

    override fun onBindViewHolder(holder: StatPurchaseViewHolder, position: Int) {
        val sale_product= purchaseModelList[position]
        holder.purchaseName.text = sale_product.name
        holder.purchasePiece.text= sale_product.piece.toString()
        holder.PurchasePrice.text = sale_product.price.toString()
        Picasso.get().load(sale_product.image).into(holder.image)
    }

    override fun getItemCount(): Int {
        return purchaseModelList.size
    }
}