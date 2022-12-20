package com.furkanbostan.depoyonetim.Store


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.furkanbostan.depoyonetim.API.ApiUtils
import com.furkanbostan.depoyonetim.Model.*
import com.furkanbostan.depoyonetim.MyModel.ProdId
import com.furkanbostan.depoyonetim.MyModel.PurchasesModel
import com.furkanbostan.depoyonetim.ViewModel.CategoryViewModel
import com.furkanbostan.depoyonetim.databinding.FragmentBuyBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BuyFragment : Fragment() {
    private var binding: FragmentBuyBinding?=null





    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBuyBinding.inflate(layoutInflater,container,false)
        val view = binding!!.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding!!.btnProductEkle.setOnClickListener{
            getCategory(binding!!.etProdKategori.text.toString())






        }

    }




    fun addProduct(productModel: ProductModel){
        val pdi = ApiUtils.getProductDaoInterface()
        val productEkle = pdi.productEkle(productModel)
        productEkle.enqueue(object : Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Log.e("productEkle","Ekleme Başarılı")

                getAllProductId()
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("productEKle",t.toString())
            }

        })

    }

    fun getCategory(categoryName:String) {

        var kategoriId = 0
        val cdi = ApiUtils.getCategoryDaoInterface()
        var kategoriList=cdi.getAllCAtegory()
        kategoriList.enqueue(object : Callback<List<CategoryViewModel>>{
            override fun onResponse(call: Call<List<CategoryViewModel>>, response: Response<List<CategoryViewModel>>) {
                val kategori_list_temp= response.body()

                for (k in kategori_list_temp!!){
                  Log.e("kategoriName",k.Name)
                    Log.e("kategoriId",k.Id.toString())
                    if (k.Name.trim().toLowerCase()==categoryName.trim().toLowerCase()){
                        Log.e("KAtegoriiiii","EŞleşme Var")
                        kategoriId=k.Id
                        Log.e("kategoriIdFor","Eşleşme olduğunda: "+ kategoriId)
                    }
                }

                val prodName=binding!!.etProdName.text.toString()
                val prodDesc = binding!!.etProdDEsc.text.toString()
                val prodPrice=binding!!.etProductSalePrice.text.toString().toInt().toFloat()
                val categoryId = kategoriId
                Log.e("asdasdasdasd",categoryId.toString())
                val productModel =ProductModel(categoryId,prodName,prodDesc,prodPrice)

                addProduct(productModel)

            }

            override fun onFailure(call: Call<List<CategoryViewModel>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })



    }
    fun addPurchases(purchasesModel: PurchasesModel){
        val sdi = ApiUtils.getPurchasesDaoInterface()
        var addPurchase = sdi.addPurchase(purchasesModel)
        addPurchase.enqueue(object :Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Log.e("addPurchase","Başarılı")
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("addPurchase",t.toString())
            }

        })
    }




    fun getAllProductId(){
        val prodIdArrayList= ArrayList<Int>()
        val prodGetir= ApiUtils.getProductDaoInterface()
        val product_temp = prodGetir.poductIdGetir()
        product_temp.enqueue(object :Callback<List<ProdId>>{
            override fun onResponse(call: Call<List<ProdId>>, response: Response<List<ProdId>>) {
               val prodId_temp = response.body()
                for (k in prodId_temp!!){
                    prodIdArrayList.add(k.id)
                    Log.e("id", k.id.toString())
                }
                val purchaseQuantity = binding!!.etProdQuantity.text.toString().toInt()
                val purchasePrice = binding!!.etProdPurchasePrice.text.toString().toFloat()
                val prodId= prodIdArrayList.last()
                val purchasesModel= PurchasesModel(prodId,purchaseQuantity,purchasePrice)
                addPurchases(purchasesModel)
            }

            override fun onFailure(call: Call<List<ProdId>>, t: Throwable) {
                TODO("Not yet implemented")
            }


        })
    }
}