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


        binding!!.fabAdd.setOnClickListener{
            onaAddFABClicked()
           // test1()
        }

        binding!!.fabBuy.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_storeFragment_to_buyFragment4)
        }
        binding!!.fabCategoryAdd.setOnClickListener{
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

    fun test1(){
        val kdi = ApiUtils.setCityDaoInterface()
        val sehirTut=kdi.sehirCagir()
        sehirTut.enqueue(object : Callback<List<CityViewModel>> {

            override fun onResponse(call: Call<List<CityViewModel>>, response: Response<List<CityViewModel>>) {
                if (response!=null){
                    val sehirListe = response.body()

                    for (k in sehirListe!!){
                        Log.e("********","***********")
                        Log.e("Sehir Id:", k.Id.toString())
                        Log.e("Sehir NAme:", k.Name)
                        //  Log.e("Stores Name:", k.Stores[1].Name)
                    }

                }
            }

            override fun onFailure(call: Call<List<CityViewModel>>, t: Throwable) {
                Log.e("Basaramadık abi",t.toString())
            }
        })

        val cdi = ApiUtils.setCityDaoInterface()
        val sehirSil = cdi.sehirSil(4)
        sehirSil.enqueue(object : Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Log.e("başarılı","asdasd")
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("hata",t.toString())
            }

        })

        val cdo = ApiUtils.setCityDaoInterface()
        val cityModel = CityModel("Palto")
        val sehirEKle = cdo.sehirEKle(cityModel)
        sehirEKle.enqueue(object : Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Log.e("sehir ekleme başarılı","başarılı")
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("sehir ekleme başarısız",t.toString())
            }

        })

        val asd = ApiUtils.getCategoryDaoInterface()
        val categoryModel = CategoryModel("Palto")
        val kategoriEkle = asd.kategoriEkle(1,categoryModel)
        kategoriEkle.enqueue(object : Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Log.e("qwewqe","kategori ekleme başarılı")
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("kategori ",t.toString())
            }

        })






    }
    //f8 miş
    // kanka neden girmiyor buraya
    /* fun test(){
         CoroutineScope(Dispatchers.IO).launch {
             try{
                 var asd = DepoApi;
                 val t = DepoApi.retrofitService.sehirCagir()
                 Log.e("Success!",t.toString())
             }catch (e:Exception){
                 Log.e("Error",e.message.toString())
             }
         }
     }*/

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
            binding!!.fabBuy.visibility=View.VISIBLE
            binding!!.fabCategoryAdd.visibility=View.VISIBLE
        }else{
            binding!!.fabBuy.visibility=View.INVISIBLE
            binding!!.fabCategoryAdd.visibility=View.INVISIBLE
        }

    }
    fun setAnimation(clicked:Boolean){
        if (!clicked){
            binding!!.fabBuy.startAnimation(fromBottom)
            binding!!.fabCategoryAdd.startAnimation(fromBottom)
            binding!!.fabAdd.startAnimation(rotateOpen)


        }else{
            binding!!.fabBuy.startAnimation(toBottom)
            binding!!.fabCategoryAdd.startAnimation(toBottom)
            binding!!.fabAdd.startAnimation(rotateClose)
        }
    }






    private fun dataInitiailize(){
       /* productArrayList = ArrayList<StoreProductModel>()

        val product = StoreProductModel("Kazak",15,"Mavi, M beden",200,"#kazak")
        val product1 = StoreProductModel("Kazak",15,"Mavi, M beden",200,"#kazak")
        val product2 = StoreProductModel("Kazak",15,"Mavi, M beden",200,"kazak")
        val product3 = StoreProductModel("Kazak",15,"Mavi, M beden",200,"kazak")
        val product4 = StoreProductModel("Kazak",15,"Mavi, M beden",200,"kazak")
        val product5 = StoreProductModel("Kazak",15,"Mavi, M beden",200,"kazak")
        val product6 = StoreProductModel("Masa",15,"Mavi, M beden",200,"kazak")


        productArrayList.add(product)
        productArrayList.add(product1)
        productArrayList.add(product2)
        productArrayList.add(product3)
        productArrayList.add(product4)
        productArrayList.add(product5)
        productArrayList.add(product6)*/

       /* categoryArrayList = ArrayList<CategoryModel>()
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
*/
    }


}