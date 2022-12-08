package com.furkanbostan.depoyonetim.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.furkanbostan.depoyonetim.Model.CategoryModel
import com.furkanbostan.depoyonetim.R

class CategoryAdapter(private val mContext : Context,
                      private val categoryArrayList: ArrayList<CategoryModel>):RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {
    inner class CategoryHolder (view : View):RecyclerView.ViewHolder(view){
        var category_name : TextView

        init {
            category_name = view.findViewById(R.id.tv_category_name)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val tasarim = LayoutInflater.from(mContext).inflate(R.layout.item_category_model,parent,false)
        return CategoryHolder(tasarim)
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        val category = categoryArrayList[position]

        holder.category_name.text = category.name
    }

    override fun getItemCount(): Int {
        return categoryArrayList.size
    }
}