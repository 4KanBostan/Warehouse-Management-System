package com.furkanbostan.depoyonetim.Store

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.furkanbostan.depoyonetim.Adapter.CategoryAdapter
import com.furkanbostan.depoyonetim.Adapter.StoreProductAdapter
import com.furkanbostan.depoyonetim.Model.CategoryModel
import com.furkanbostan.depoyonetim.Model.StoreProductModel
import com.furkanbostan.depoyonetim.R


class StoreFragment : Fragment() {

    private lateinit var adapter : StoreProductAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var productArrayList : ArrayList<StoreProductModel>

    private lateinit var adapter_category : CategoryAdapter
    private lateinit var recyclerView_category: RecyclerView
    private lateinit var categoryArrayList : ArrayList<CategoryModel>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_store, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataInitiailize()
        val layoutManager = LinearLayoutManager(view.context)
        recyclerView = view.findViewById(R.id.recyclerView_store_product)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = StoreProductAdapter(view.context,productArrayList)
        recyclerView.adapter = adapter

        val layoutManager_category = LinearLayoutManager(view.context,LinearLayoutManager.HORIZONTAL,false)
        recyclerView_category = view.findViewById(R.id.recyclerView_store_category)
        recyclerView_category.layoutManager = layoutManager_category
        recyclerView_category.setHasFixedSize(true)
        adapter_category = CategoryAdapter(view.context,categoryArrayList)
        recyclerView_category.adapter = adapter_category
    }


    private fun dataInitiailize(){
        productArrayList = ArrayList<StoreProductModel>()

        val product = StoreProductModel("Kazak",15,"Mavi, M beden",200,250)
        val product1 = StoreProductModel("Kazak",15,"Mavi, M beden",200,250)
        val product2 = StoreProductModel("Kazak",15,"Mavi, M beden",200,250)
        val product3 = StoreProductModel("Kazak",15,"Mavi, M beden",200,250)
        val product4 = StoreProductModel("Kazak",15,"Mavi, M beden",200,250)
        val product5 = StoreProductModel("Kazak",15,"Mavi, M beden",200,250)
        val product6 = StoreProductModel("Kazak",15,"Mavi, M beden",200,250)

        productArrayList.add(product)
        productArrayList.add(product1)
        productArrayList.add(product2)
        productArrayList.add(product3)
        productArrayList.add(product4)
        productArrayList.add(product5)
        productArrayList.add(product6)

        categoryArrayList = ArrayList<CategoryModel>()
        val category = CategoryModel("Erkek Gömlek")
        val category1 = CategoryModel("Kazak")
        val category2 = CategoryModel("Erkek Gömlek")
        val category3 = CategoryModel("Erkek Gömlek")
        val category4 = CategoryModel("Erkek Gömlek")

        categoryArrayList.add(category)
        categoryArrayList.add(category1)
        categoryArrayList.add(category2)
        categoryArrayList.add(category3)
        categoryArrayList.add(category4)

    }


}