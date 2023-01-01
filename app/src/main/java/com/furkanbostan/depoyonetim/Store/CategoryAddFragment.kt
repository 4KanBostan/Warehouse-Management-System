package com.furkanbostan.depoyonetim.Store

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.furkanbostan.depoyonetim.API.ApiUtils
import com.furkanbostan.depoyonetim.Adapter.CategoryAddAdapter
import com.furkanbostan.depoyonetim.Adapter.StoreProductAdapter
import com.furkanbostan.depoyonetim.Model.CategoryModel
import com.furkanbostan.depoyonetim.R
import com.furkanbostan.depoyonetim.ViewModel.CategoryViewModel
import com.furkanbostan.depoyonetim.databinding.FragmentCategoryAddBinding
import com.furkanbostan.depoyonetim.databinding.FragmentStatusBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryAddFragment : Fragment() {

    private lateinit var binding: FragmentCategoryAddBinding

    private lateinit var categoryList: ArrayList<CategoryModel>
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CategoryAddAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCategoryAddBinding.inflate(layoutInflater,container,false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryList= ArrayList<CategoryModel>()
        val layoutManager = LinearLayoutManager(view.context)
        recyclerView = view.findViewById(R.id.recyclerView_categoryAdd)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = CategoryAddAdapter(view.context,categoryList)
        recyclerView.adapter = adapter
        getCategory()


        binding!!.buttonAddCategory.setOnClickListener{
            addCategory(binding!!.etCategoryAdd.text.toString())
        }
        binding.backToStore3.setOnClickListener{
            findNavController().popBackStack()
        }



    }



    fun getCategory(){

        val cdi = ApiUtils.getCategoryDaoInterface()
        var kategoriList=cdi.getAllCAtegory()
        kategoriList.enqueue(object : Callback<List<CategoryViewModel>>{
            override fun onResponse(call: Call<List<CategoryViewModel>>, response: Response<List<CategoryViewModel>>) {
                val kategori_list_temp= response.body()
                for (k in kategori_list_temp!!){
                    val temp = CategoryModel(k.Name)
                    categoryList.add(temp)
                }
                adapter.setNewList(categoryList)
            }

            override fun onFailure(call: Call<List<CategoryViewModel>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }



    fun addCategory(categoryName :String){
        val cdi = ApiUtils.getCategoryDaoInterface()
        val categoryModel = CategoryModel(categoryName)
        val kategoriEkle = cdi.kategoriEkle(1,categoryModel)
        kategoriEkle.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Log.e("qwewqe","kategori ekleme başarılı")
                val temp = CategoryModel(categoryName)
                categoryList.add(temp)
                adapter.setNewList(categoryList)
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("kategori ",t.toString())
            }

        })
    }

}