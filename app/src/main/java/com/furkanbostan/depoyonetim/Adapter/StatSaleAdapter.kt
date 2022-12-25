package com.furkanbostan.depoyonetim.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.furkanbostan.depoyonetim.Model.StoreProductModel
import com.furkanbostan.depoyonetim.R
import com.furkanbostan.depoyonetim.Status.StatsSaleModel
import com.squareup.picasso.Picasso

class StatSaleAdapter(private val mContext: Context, private var saleModelList :ArrayList<StatsSaleModel>) :RecyclerView.Adapter<StatSaleAdapter.SaleProductHolder>() {


    fun setFilteredList(filteredList: ArrayList<StatsSaleModel>) {
        this.saleModelList = filteredList
        notifyDataSetChanged()
    }

    inner class SaleProductHolder(view : View):RecyclerView.ViewHolder(view){
        var saleName: TextView
        var salePiece: TextView
        var salePrice: TextView
        var image :ImageView

        init {
            saleName = view.findViewById(R.id.tv_sale_name)
            salePiece= view.findViewById(R.id.tv_sale_piece)
            salePrice = view.findViewById(R.id.tv_sale_price)
            image = view.findViewById(R.id.stat_image_photo)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaleProductHolder {
        val tasarim = LayoutInflater.from(mContext).inflate(R.layout.item_stat_sale,parent,false)
        return SaleProductHolder(tasarim)
    }

    override fun onBindViewHolder(holder: SaleProductHolder, position: Int) {
        val sale_product= saleModelList[position]
        holder.saleName.text = sale_product.name
        holder.salePiece.text= sale_product.piece.toString()
        holder.salePrice.text = sale_product.price.toString()
        Picasso.get().load(sale_product.image).into(holder.image)
    }

    override fun getItemCount(): Int {
        return saleModelList.size
    }
}