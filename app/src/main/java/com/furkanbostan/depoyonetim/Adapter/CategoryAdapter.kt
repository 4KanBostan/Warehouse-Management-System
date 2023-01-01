package com.furkanbostan.depoyonetim.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.furkanbostan.depoyonetim.Model.CategoryModel
import com.furkanbostan.depoyonetim.R
import com.furkanbostan.depoyonetim.Store.StoreFragment

class CategoryAdapter(private val mContext : Context,
                      private var categoryArrayList: ArrayList<CategoryModel>):RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {

    private var storeFragment =  StoreFragment()

    fun setNewList(newList: ArrayList<CategoryModel>) {
        this.categoryArrayList = newList
        notifyDataSetChanged()
    }
    inner class CategoryHolder (view : View):RecyclerView.ViewHolder(view){
        var category_name : TextView
        var category_card :CardView

        init {
            category_name = view.findViewById(R.id.tv_category_name)
            category_card = view.findViewById(R.id.store_category_card)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val tasarim = LayoutInflater.from(mContext).inflate(R.layout.item_category_model,parent,false)
        return CategoryHolder(tasarim)
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        val category = categoryArrayList[position]

        holder.category_name.text = category.name
        holder.category_card.setOnClickListener{
            storeFragment.filteredList(category.name)
        }
    }

    override fun getItemCount(): Int {
        return categoryArrayList.size
    }
}