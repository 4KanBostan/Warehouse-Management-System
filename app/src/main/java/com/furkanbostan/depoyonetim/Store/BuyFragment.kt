package com.furkanbostan.depoyonetim.Store


import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.furkanbostan.depoyonetim.API.ApiUtils
import com.furkanbostan.depoyonetim.Model.*
import com.furkanbostan.depoyonetim.MyModel.PhotoModel
import com.furkanbostan.depoyonetim.MyModel.ProdId
import com.furkanbostan.depoyonetim.MyModel.PurchasesModel
import com.furkanbostan.depoyonetim.MyModel.WalletModel
import com.furkanbostan.depoyonetim.ViewModel.CategoryViewModel
import com.furkanbostan.depoyonetim.ViewModel.ProductViewModel
import com.furkanbostan.depoyonetim.ViewModel.WalletViewModel
import com.furkanbostan.depoyonetim.databinding.FragmentBuyBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BuyFragment : Fragment() {
    private lateinit var binding: FragmentBuyBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBuyBinding.inflate(layoutInflater,container,false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnProductEkle.setOnClickListener{
            getWallet()
        }

        binding.backToStore2.setOnClickListener{
            findNavController().popBackStack()
        }

    }


    fun addPurchases(purchasesModel: PurchasesModel){
        val sdi = ApiUtils.getPurchasesDaoInterface()
        var addPurchase = sdi.addPurchase(purchasesModel)
        addPurchase.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Log.e("addPurchase","Başarılı")
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("addPurchase",t.toString())
            }

        })
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
                val prodImage = binding!!.etPhotoUrl.text.toString()
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
    fun addPhoto(photoModel: PhotoModel){
        val pdi= ApiUtils.getPhotosDaoInterface()
        val addPhoto = pdi.addPhoto(photoModel)
        addPhoto.enqueue(object :Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Log.e("Photo add","Başarılı")
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                TODO("Not yet implemented")
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
                val photoModel=PhotoModel(purchasesModel.productId,binding!!.etPhotoUrl.text.toString())
                addPhoto(photoModel)
                Handler().postDelayed({
                    addPurchases(purchasesModel)
                }, 3000)

            }

            override fun onFailure(call: Call<List<ProdId>>, t: Throwable) {
                TODO("Not yet implemented")
            }


        })
    }
    fun getWallet(){
        val wdi = ApiUtils.getWalleteDaoInterface()
        val getWallet = wdi.getWallets()

        var quantity = binding.etProdQuantity.text.toString().toInt()
        var price = binding.etProdPurchasePrice.text.toString().toFloat()
        var temp = quantity*price
        getWallet.enqueue(object : Callback<List<WalletViewModel>>{
            override fun onResponse(call: Call<List<WalletViewModel>>, response: Response<List<WalletViewModel>>) {
               val walletTemp = response.body()
                if (walletTemp != null) {
                    if (walletTemp.first().Balance>=temp){
                        val income = walletTemp.first().Income
                        val outGOing = walletTemp.first().OutGoing+temp
                        val balance = walletTemp.first().Balance-temp
                        val walletModel = WalletModel(1,income,outGOing,balance)
                        updateWallet(1,walletModel)
                        getCategory(binding.etProdKategori.text.toString())
                    }else Toast.makeText(activity,"Bakiye yetersiz",Toast.LENGTH_SHORT).show()

                }
            }

            override fun onFailure(call: Call<List<WalletViewModel>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun updateWallet(walletId:Int, walletModel: WalletModel) {
        val wdi = ApiUtils.getWalleteDaoInterface()
        val updWallet = wdi.updateWallet(walletId,walletModel)
        updWallet.enqueue(object : Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Log.e("updateWallet","updateWallet completed")
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("updateWallet",t.toString())
            }

        })

    }


}