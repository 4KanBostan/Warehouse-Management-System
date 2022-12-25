package com.furkanbostan.depoyonetim.Status

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.furkanbostan.depoyonetim.API.ApiUtils
import com.furkanbostan.depoyonetim.Adapter.StatPurchaseAdapter
import com.furkanbostan.depoyonetim.Adapter.StatSaleAdapter
import com.furkanbostan.depoyonetim.MyModel.ProdIdPhotos
import com.furkanbostan.depoyonetim.R
import com.furkanbostan.depoyonetim.ViewModel.PurchasesViewModel
import com.furkanbostan.depoyonetim.ViewModel.SaleViewModel
import com.furkanbostan.depoyonetim.databinding.FragmentStatusBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StatusFragment : Fragment() {
    private var binding: FragmentStatusBinding? = null

    private lateinit var adapter_purchase: StatPurchaseAdapter
    private lateinit var recyclerView_purchase: RecyclerView
    private lateinit var statPurchaseList: ArrayList<StatsPurchaseModel>

    private lateinit var adapter_sale: StatSaleAdapter
    private lateinit var recyclerView_sale: RecyclerView
    private lateinit var statSaleList: ArrayList<StatsSaleModel>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentStatusBinding.inflate(layoutInflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getALlPurchases()
        val layoutManagerPurchase = LinearLayoutManager(view.context)
        recyclerView_purchase = view.findViewById(R.id.recyclerView_purchase)
        recyclerView_purchase.layoutManager = layoutManagerPurchase
        recyclerView_purchase.setHasFixedSize(true)
        adapter_purchase = StatPurchaseAdapter(view.context, statPurchaseList)
        recyclerView_purchase.adapter = adapter_purchase


        getAllSales()
        val layoutManagerSale = LinearLayoutManager(view.context)
        recyclerView_sale = view.findViewById(R.id.recyclerView_sale)
        recyclerView_sale.layoutManager = layoutManagerSale
        recyclerView_sale.setHasFixedSize(true)
        adapter_sale = StatSaleAdapter(view.context, statSaleList)
        recyclerView_sale.adapter = adapter_sale

        Handler().postDelayed({
            binding!!.tvKarZarar.text = (binding!!.tvSalePrice.text.toString().toFloat()-binding!!.tvPurchasePrice.text.toString().toFloat()).toString()
        }, 2000)


    }


    fun getAllSales() {
        statSaleList = ArrayList<StatsSaleModel>()
        val sdi = ApiUtils.getSaleDaoInterface()
        val saleArray = sdi.getAllSale()
        saleArray.enqueue(object : Callback<List<SaleViewModel>> {
            override fun onResponse(
                call: Call<List<SaleViewModel>>,
                response: Response<List<SaleViewModel>>
            ) {
                var tempPrice = 0f
                var tempQuantity = 0
                val saleListTemp = response.body()
                for (k in saleListTemp!!) {
                    tempQuantity += k.Quantity
                    tempPrice += k.Quantity * k.Product.Price
                    val saleModel =
                        StatsSaleModel(k.ProductId,k.Product.Name, k.Quantity, k.Product.Price)
                    getAllProductsIdAndPhotoSale(saleModel)
                }
                binding!!.tvSaleQuantity.text = tempQuantity.toString()
                binding!!.tvSalePrice.text = tempPrice.toString()


            }

            override fun onFailure(call: Call<List<SaleViewModel>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun getALlPurchases() {
        statPurchaseList = ArrayList<StatsPurchaseModel>()
        val sdi = ApiUtils.getPurchasesDaoInterface()
        val purchaseArray = sdi.gelAllpurchase()
        purchaseArray.enqueue(object : Callback<List<PurchasesViewModel>> {
            override fun onResponse(
                call: Call<List<PurchasesViewModel>>,
                response: Response<List<PurchasesViewModel>>
            ) {
                var tempPrice = 0f
                var tempQuantity = 0
                val purchaseArrayTemp = response.body()
                for (k in purchaseArrayTemp!!) {
                    tempQuantity += k.Quantity
                    tempPrice += k.Quantity * k.Price
                    val purchaseModel =
                        StatsPurchaseModel(k.ProductId,k.Product.Name, k.Quantity, k.Price)
                    getAllProductsIdAndPhotosPurchase(purchaseModel)
                }
                binding!!.tvPurchaseQuantity.text = tempQuantity.toString()
                binding!!.tvPurchasePrice.text = tempPrice.toString()


            }

            override fun onFailure(call: Call<List<PurchasesViewModel>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun getAllProductsIdAndPhotosPurchase(statsPurchaseModel: StatsPurchaseModel){

        val pdi = ApiUtils.getProductDaoInterface()
        val getIdPhoto = pdi.productIDAndPhotos()

        getIdPhoto.enqueue(object : Callback<List<ProdIdPhotos>>{
            override fun onResponse(call: Call<List<ProdIdPhotos>>, response: Response<List<ProdIdPhotos>>) {
                val arrayTemp = response.body()

                for (k in arrayTemp!!){
                    if (statsPurchaseModel.id==k.id)
                    statsPurchaseModel.image= k.photos.first().Path
                }
                statPurchaseList.add(statsPurchaseModel)
                adapter_purchase.setNewList(statPurchaseList)
            }

            override fun onFailure(call: Call<List<ProdIdPhotos>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }

    fun getAllProductsIdAndPhotoSale(statsSaleModel: StatsSaleModel){

        val pdi = ApiUtils.getProductDaoInterface()
        val getIdPhoto = pdi.productIDAndPhotos()

        getIdPhoto.enqueue(object : Callback<List<ProdIdPhotos>>{
            override fun onResponse(call: Call<List<ProdIdPhotos>>, response: Response<List<ProdIdPhotos>>) {
                val arrayTemp = response.body()

                for (k in arrayTemp!!){
                    if (statsSaleModel.id==k.id){
                        statsSaleModel.image= k.photos.first().Path
                    }

                }
                statSaleList.add(statsSaleModel)
                adapter_sale.setFilteredList(statSaleList)
            }

            override fun onFailure(call: Call<List<ProdIdPhotos>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }


}