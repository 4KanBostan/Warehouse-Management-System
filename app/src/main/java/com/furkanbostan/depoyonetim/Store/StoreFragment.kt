package com.furkanbostan.depoyonetim.Store

import android.widget.SearchView;
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils

import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.furkanbostan.depoyonetim.API.ApiUtils
import com.furkanbostan.depoyonetim.Adapter.CategoryAdapter
import com.furkanbostan.depoyonetim.Adapter.StoreProductAdapter
import com.furkanbostan.depoyonetim.Model.CategoryModel
import com.furkanbostan.depoyonetim.Model.ProductModel
import com.furkanbostan.depoyonetim.Model.StoreProductModel
import com.furkanbostan.depoyonetim.R
import com.furkanbostan.depoyonetim.MyModel.CityModel
import com.furkanbostan.depoyonetim.ViewModel.CategoryViewModel
import com.furkanbostan.depoyonetim.ViewModel.CityViewModel
import com.furkanbostan.depoyonetim.ViewModel.ProductViewModel
import com.furkanbostan.depoyonetim.databinding.FragmentStoreBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class StoreFragment : Fragment() {
    private lateinit var binding:FragmentStoreBinding

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
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productArrayList = ArrayList<StoreProductModel>()
        getAllProduct()
        val layoutManager_product = LinearLayoutManager(view.context)
        recyclerView_product = view.findViewById(R.id.recyclerView_store_product)
        recyclerView_product.layoutManager = layoutManager_product
        recyclerView_product.setHasFixedSize(true)
        adapter_product = StoreProductAdapter(view.context,productArrayList)
        recyclerView_product.adapter = adapter_product

        categoryArrayList = ArrayList<CategoryModel>()
        getAllCategory()
        val layoutManager_category = LinearLayoutManager(view.context,LinearLayoutManager.HORIZONTAL,false)
        recyclerView_category = view.findViewById(R.id.recyclerView_store_category)
        recyclerView_category.layoutManager = layoutManager_category
        recyclerView_category.setHasFixedSize(true)
        adapter_category = CategoryAdapter(view.context,categoryArrayList)
        recyclerView_category.adapter = adapter_category


        binding.fabAdd.setOnClickListener{
            onaAddFABClicked()

        }

        binding.fabBuy.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_storeFragment_to_buyFragment4)
        }
        binding.fabCategoryAdd.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_storeFragment_to_categoryAddFragment)
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
    fun getAllProduct(){
        val prodGetir= ApiUtils.getProductDaoInterface()
        val product_temp = prodGetir.productGetir()
        product_temp.enqueue(object : Callback<List<ProductViewModel>>{
            override fun onResponse(call: Call<List<ProductViewModel>>, response: Response<List<ProductViewModel>>) {
                if (response!=null){
                    val productList = response.body()
                    var purchaseTemp=0
                    var saleTemp = 0
                    var quantity=0

                    if (productList != null) {
                        for (k in productList){
                            for (i in k.Purchases){
                                purchaseTemp +=i.Quantity
                            }
                            for (j in k.Sales){
                                saleTemp +=j.Quantity
                            }
                            if (purchaseTemp-saleTemp>0){
                                quantity= purchaseTemp-saleTemp
                            }

                            val temp= StoreProductModel(k.Id,k.Name,quantity,k.Description,k.Price,k.Purchases.last().Price,
                                                        k.Category.Name,k.Photo.first().Path)
                            productArrayList.add(temp)
                            purchaseTemp=0
                            saleTemp = 0
                        }
                        adapter_product.setFilteredList(productArrayList)
                    }
                }
            }
            override fun onFailure(call: Call<List<ProductViewModel>>, t: Throwable) {
                Log.d("productÇek",t.toString())
            }

        })
    }

    fun getAllCategory(){
        val cdi = ApiUtils.getCategoryDaoInterface()
        var kategoriList=cdi.getAllCAtegory()
        kategoriList.enqueue(object : Callback<List<CategoryViewModel>>{
            override fun onResponse(call: Call<List<CategoryViewModel>>, response: Response<List<CategoryViewModel>>) {
                val kategori_list_temp= response.body()
                for (k in kategori_list_temp!!){
                    val temp = CategoryModel(k.Name)
                    categoryArrayList.add(temp)
                }
                adapter_category.setNewList(categoryArrayList)
            }

            override fun onFailure(call: Call<List<CategoryViewModel>>, t: Throwable) {
                Log.e("kategoriHata",t.toString())
            }
        })
    }


    fun filteredList(name :String){
        lateinit var filteredList :ArrayList<StoreProductModel>
        filteredList = ArrayList<StoreProductModel>()

            if (name.isNullOrEmpty()){
                adapter_product.setFilteredList(productArrayList)
            }else {
                for (i in productArrayList) {
                    if (i.name.toLowerCase() == name.toLowerCase() || name.toLowerCase()==i.category_name.toLowerCase()) {
                        filteredList.add(i)
                    }
                }

                if (filteredList.isEmpty()) {

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
            binding.fabBuy.visibility=View.VISIBLE
            binding.fabCategoryAdd.visibility=View.VISIBLE
        }else{
            binding.fabBuy.visibility=View.INVISIBLE
            binding.fabCategoryAdd.visibility=View.INVISIBLE
        }
    }
    fun setAnimation(clicked:Boolean){
        if (!clicked){
            binding.fabBuy.startAnimation(fromBottom)
            binding.fabCategoryAdd.startAnimation(fromBottom)
            binding.fabAdd.startAnimation(rotateOpen)
        }else{
            binding.fabBuy.startAnimation(toBottom)
            binding.fabCategoryAdd.startAnimation(toBottom)
            binding.fabAdd.startAnimation(rotateClose)
        }
    }
}