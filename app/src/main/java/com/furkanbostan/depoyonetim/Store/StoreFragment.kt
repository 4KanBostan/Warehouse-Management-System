package com.furkanbostan.depoyonetim.Store

import android.app.SearchManager;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast

import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.furkanbostan.depoyonetim.Adapter.CategoryAdapter
import com.furkanbostan.depoyonetim.Adapter.StoreProductAdapter
import com.furkanbostan.depoyonetim.Model.CategoryModel
import com.furkanbostan.depoyonetim.Model.StoreProductModel
import com.furkanbostan.depoyonetim.R
import com.furkanbostan.depoyonetim.databinding.FragmentStoreBinding


class StoreFragment : Fragment() {
    private var binding:FragmentStoreBinding?=null

    //extended Fab Button Animasyon İşlemleri
    private val rotateOpen:Animation by lazy{AnimationUtils.loadAnimation(context,R.anim.rotate_open_anim)}
    private val rotateClose:Animation by lazy{AnimationUtils.loadAnimation(context,R.anim.rotate_close_anim)}
    private val fromBottom:Animation by lazy{AnimationUtils.loadAnimation(context,R.anim.from_bottom_anim)}
    private val toBottom:Animation by lazy{AnimationUtils.loadAnimation(context,R.anim.to_bottom_anim)}

    private var clicked =false

    private lateinit var adapter_product : StoreProductAdapter
    private lateinit var recyclerView_product: RecyclerView
    private lateinit var productArrayList : ArrayList<StoreProductModel>

    private lateinit var adapter_category : CategoryAdapter
    private lateinit var recyclerView_category: RecyclerView
    private lateinit var categoryArrayList : ArrayList<CategoryModel>



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding=FragmentStoreBinding.inflate(layoutInflater,container,false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataInitiailize()
        val layoutManager_product = LinearLayoutManager(view.context)
        recyclerView_product = view.findViewById(R.id.recyclerView_store_product)
        recyclerView_product.layoutManager = layoutManager_product
        recyclerView_product.setHasFixedSize(true)
        adapter_product = StoreProductAdapter(view.context,productArrayList)
        recyclerView_product.adapter = adapter_product

        val layoutManager_category = LinearLayoutManager(view.context,LinearLayoutManager.HORIZONTAL,false)
        recyclerView_category = view.findViewById(R.id.recyclerView_store_category)
        recyclerView_category.layoutManager = layoutManager_category
        recyclerView_category.setHasFixedSize(true)
        adapter_category = CategoryAdapter(view.context,categoryArrayList)
        recyclerView_category.adapter = adapter_category

        binding!!.fabAdd.setOnClickListener{
            onaAddFABClicked()
        }

        binding!!.fabSale.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_storeFragment_to_saleFragment)
        }
        binding!!.fabBuy.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_storeFragment_to_buyFragment)
        }

        val searchView = view.findViewById<SearchView>(R.id.searchview) as SearchView
        searchView.clearFocus()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText !== null) {
                    filteredList(newText)
                }
                return true
            }

        })

    }

    fun filteredList(text :String){
        lateinit var filteredList :ArrayList<StoreProductModel>
        filteredList = ArrayList<StoreProductModel>()

            if (text.isNullOrEmpty()){
                adapter_product.setFilteredList(productArrayList)
            }else {
                for (i in productArrayList) {
                    if (i.name.toLowerCase() == text.toLowerCase()) {
                        filteredList.add(i)
                    }
                }

                if (filteredList.isEmpty()) {
                    //Toast.makeText(context,"Ürün eşleşmiyor",Toast.LENGTH_SHORT).show()
                } else {
                    adapter_product.setFilteredList(filteredList)
                }
            }


    }

    fun onaAddFABClicked(){
        setVisibility(clicked)
        setAnimation(clicked)
        clicked = !clicked
    }

    fun setVisibility(clicked:Boolean){
        if (!clicked){
            binding!!.fabSale.visibility=View.VISIBLE
            binding!!.fabBuy.visibility=View.VISIBLE
        }else{
            binding!!.fabSale.visibility=View.INVISIBLE
            binding!!.fabBuy.visibility=View.INVISIBLE
        }

    }
    fun setAnimation(clicked:Boolean){
        if (!clicked){
            binding!!.fabSale.startAnimation(fromBottom)
            binding!!.fabBuy.startAnimation(fromBottom)
            binding!!.fabAdd.startAnimation(rotateOpen)


        }else{
            binding!!.fabSale.startAnimation(toBottom)
            binding!!.fabBuy.startAnimation(toBottom)
            binding!!.fabAdd.startAnimation(rotateClose)
        }
    }






    private fun dataInitiailize(){
        productArrayList = ArrayList<StoreProductModel>()

        val product = StoreProductModel("Kazak",15,"Mavi, M beden",200,250)
        val product1 = StoreProductModel("Kazak",15,"Mavi, M beden",200,250)
        val product2 = StoreProductModel("Kazak",15,"Mavi, M beden",200,250)
        val product3 = StoreProductModel("Kazak",15,"Mavi, M beden",200,250)
        val product4 = StoreProductModel("Kazak",15,"Mavi, M beden",200,250)
        val product5 = StoreProductModel("Kazak",15,"Mavi, M beden",200,250)
        val product6 = StoreProductModel("Masa",15,"Mavi, M beden",200,250)


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