package com.furkanbostan.depoyonetim.Store

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.furkanbostan.depoyonetim.API.ApiUtils
import com.furkanbostan.depoyonetim.MyModel.PurchasesModel
import com.furkanbostan.depoyonetim.MyModel.SaleModel
import com.furkanbostan.depoyonetim.MyModel.WalletModel
import com.furkanbostan.depoyonetim.ViewModel.ProductViewModel
import com.furkanbostan.depoyonetim.ViewModel.StoreViewModel
import com.furkanbostan.depoyonetim.ViewModel.WalletViewModel
import com.furkanbostan.depoyonetim.databinding.FragmentProductDetailsBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductDetailsFragment : Fragment() {

    private lateinit var binding:FragmentProductDetailsBinding
    val args: ProductDetailsFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProductDetailsBinding.inflate(layoutInflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getStore()
        getProduct()

        binding.backToStore.setOnClickListener{
            findNavController().popBackStack()
        }

        binding.btnDetailsPurchase.setOnClickListener{
            getWalletForPurchase()
        }

        binding.btnDetailsSale.setOnClickListener{
            val quantitySale= binding.etDetailsProdQuantity.text.toString().toInt()
            val prodQuantity = binding.tvProdQuantity.text.toString().toInt()
            if (prodQuantity>=quantitySale){
                getWalletForSales()
            }else Toast.makeText(activity,"Yeterli ürün yok",Toast.LENGTH_SHORT).show()

        }
    }

    fun addPurchase(purchasesModel: PurchasesModel){
        val pdi = ApiUtils.getPurchasesDaoInterface()
        val addPurchase = pdi.addPurchase(purchasesModel)
       addPurchase.enqueue(object : Callback<Void>{
           override fun onResponse(call: Call<Void>, response: Response<Void>) {
               Toast.makeText(activity,"Ürün satın alınmıştır",Toast.LENGTH_SHORT).show()
               getProduct()
           }

           override fun onFailure(call: Call<Void>, t: Throwable) {
               Log.e("addPurchase",t.toString())
           }

       })
    }

    fun addSale(saleModel: SaleModel){
        val sdi = ApiUtils.getSaleDaoInterface()
        val addSale = sdi.addSale(saleModel)
        addSale.enqueue(object :Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Toast.makeText(activity,"Ürün satıldı",Toast.LENGTH_SHORT).show()
                getProduct()
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun getStore(){
        val sdi = ApiUtils.getStoreDaoInterface()
        val getStore = sdi.getStore()
        getStore.enqueue(object : Callback<List<StoreViewModel>>{
            override fun onResponse(call: Call<List<StoreViewModel>>, response: Response<List<StoreViewModel>>) {
                val arrayStoreTemp = response.body()
                val store = arrayStoreTemp!!.get(0)
                binding.tvStoreName.setText(store.Name)
                binding.tvProdStoreAddress.setText(store.Address)
            }

            override fun onFailure(call: Call<List<StoreViewModel>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun getProduct(){
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

                            if (k.Id==args.productId){

                                for (i in k.Purchases){
                                    purchaseTemp +=i.Quantity
                                }
                                for (j in k.Sales){
                                    saleTemp +=j.Quantity
                                }
                                if (purchaseTemp-saleTemp>0){
                                    quantity= purchaseTemp-saleTemp
                                }

                                binding.tvProdQuantity.setText(quantity.toString())
                                binding.tvDetailsPurchasePrice.setText(k.Purchases.last().Price.toString())
                                binding.tvDetailSalePrice.setText(k.Price.toString())
                                binding.tvProdNAme.setText(k.Name)
                                binding.tvProdDetails.setText(k.Description)
                                Picasso.get().load(k.Photo.first().Path).into(binding.imgProdDetails)
                                purchaseTemp=0
                                saleTemp = 0
                            }
                        }
                    }
                }
            }
            override fun onFailure(call: Call<List<ProductViewModel>>, t: Throwable) {
                Log.d("productÇek",t.toString())
            }

        })
    }

    fun getWalletForPurchase(){
        val wdi = ApiUtils.getWalleteDaoInterface()
        val getWallet = wdi.getWallets()

        getWallet.enqueue(object : Callback<List<WalletViewModel>>{
            override fun onResponse(call: Call<List<WalletViewModel>>, response: Response<List<WalletViewModel>>) {
                val arrayWAlletTemp = response.body()
                val price= binding.tvDetailsPurchasePrice.text.toString().toFloat()
                val quantity= Integer.parseInt(binding.etDetailsProdQuantity.text.toString())
                val totalPrice =price*quantity
                val purchasesModel = PurchasesModel(args.productId, quantity, price)

                if (arrayWAlletTemp != null) {
                    var balance =arrayWAlletTemp.first().Balance
                    var outgoing = arrayWAlletTemp.first().OutGoing
                    val income = arrayWAlletTemp.first().Income
                    if (balance>=totalPrice){
                        val updOutgoing = outgoing+totalPrice
                        val updBalance = balance-totalPrice
                        var outGoingWalletModel = WalletModel(1,income,updOutgoing,updBalance)
                        updateWallet(1,outGoingWalletModel)
                        addPurchase(purchasesModel)
                    }else{
                        Toast.makeText(activity,"Yetersiz bakiye",Toast.LENGTH_SHORT).show()
                    }

                }
            }

            override fun onFailure(call: Call<List<WalletViewModel>>, t: Throwable) {
                Log.e("getWallet",t.toString())
            }

        })
    }

    fun getWalletForSales(){
        val wdi = ApiUtils.getWalleteDaoInterface()
        val getWallet = wdi.getWallets()

        getWallet.enqueue(object : Callback<List<WalletViewModel>>{
            override fun onResponse(call: Call<List<WalletViewModel>>, response: Response<List<WalletViewModel>>) {
                val arrayWAlletTemp = response.body()
                val price= binding.tvDetailSalePrice.text.toString().toFloat()
                val quantity= Integer.parseInt(binding.etDetailsProdQuantity.text.toString())
                val totalPrice =price*quantity
                val saleModel = SaleModel(args.productId,quantity)

                if (arrayWAlletTemp != null) {
                    var balance =arrayWAlletTemp.first().Balance
                    var outgoing = arrayWAlletTemp.first().OutGoing
                    val income = arrayWAlletTemp.first().Income

                    val updIncome = income+totalPrice
                    val updBalance = balance+totalPrice
                    var walletModel = WalletModel(1,updIncome,outgoing,updBalance)
                    updateWallet(1,walletModel)
                    addSale(saleModel)

                }
            }

            override fun onFailure(call: Call<List<WalletViewModel>>, t: Throwable) {
                Log.e("getWallet",t.toString())
            }

        })
    }

    fun updateWallet(walletId:Int, walletModel: WalletModel) {
        Log.e("new balance: ",walletModel.balance.toString())
        Log.e("new outgoing: ", walletModel.outGoing.toString())
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