package com.furkanbostan.depoyonetim.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.furkanbostan.depoyonetim.Model.CategoryModel
import com.furkanbostan.depoyonetim.Model.StoreProductModel
import com.furkanbostan.depoyonetim.R
import java.math.MathContext

class CategoryAddAdapter(private val mContext: Context,
                         private var categoryArrayList: ArrayList<CategoryModel>):RecyclerView.Adapter<CategoryAddAdapter.categoryAddVievHolder>() {

    fun setNewList(newList: ArrayList<CategoryModel>) {
        this.categoryArrayList = newList
        notifyDataSetChanged()
    }
    inner class categoryAddVievHolder(view:View):RecyclerView.ViewHolder(view){
        var categoryName :TextView
        init {
            categoryName = view.findViewById(R.id.category_add_name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): categoryAddVievHolder {
        val tasarim = LayoutInflater.from(mContext).inflate(R.layout.item_category_add,parent,false)
        return categoryAddVievHolder(tasarim)
    }

    override fun onBindViewHolder(holder: categoryAddVievHolder, position: Int) {
        holder.categoryName.text = categoryArrayList[position].name
    }

    override fun getItemCount(): Int {
        return categoryArrayList.size
    }
}