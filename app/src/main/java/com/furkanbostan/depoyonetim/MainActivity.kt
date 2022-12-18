package com.furkanbostan.depoyonetim

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.furkanbostan.depoyonetim.API.ApiUtils
import com.furkanbostan.depoyonetim.API.DepoApi
import com.furkanbostan.depoyonetim.API.Interface.CityDaoInterface
import com.furkanbostan.depoyonetim.ViewModel.CityViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navhostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        //navHostFragment ile bottomNavCOnttroller birleştirme işlemi
        NavigationUI.setupWithNavController(button, navhostFragment.navController)

        /* val BASE_URL="https://127.0.0.1:7240/api/cities/"


      *//*  val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(CityDaoInterface::class.java)

        val retrofitDAta = retrofitBuilder.sehirCagir()
        retrofitDAta.enqueue(object : Callback<List<CityViewModel>>{

            override fun onResponse(call: Call<List<CityViewModel>>, response: Response<List<CityViewModel>>) {
                val sehirListe = response.body()
                for (k in sehirListe!!){
                    Log.e("********","***********")
                    Log.e("Sehir Id:", k.Id.toString())
                    Log.e("Sehir NAme:", k.Name)
                    //  Log.e("Stores Name:", k.Stores[1].Name)
                }

            }

            override fun onFailure(call: Call<List<CityViewModel>>, t: Throwable) {
                Log.e("sd",t.toString())
            }
        })

*/

        val navController = navhostFragment.navController
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        //burada navHostFragment dinlenir
        //hedef olarak gösterilen fragmenta geçerken yaptırmak istediklerimizi içine yazarız
        navController.addOnDestinationChangedListener { navController: NavController, navDestination: NavDestination, bundle: Bundle? ->
            if (navDestination.id == R.id.homeFragment) {
                bottomNav.visibility = View.VISIBLE
            }
            if (navDestination.id == R.id.editProfilFragment) {
                bottomNav.visibility = View.GONE
            }
            if (navDestination.id == R.id.editStoreFragment) {
                bottomNav.visibility = View.GONE
            }
            if (navDestination.id == R.id.loginFragment) {
                bottomNav.visibility = View.GONE
            }

        }

        KeyboardVisibilityEvent.setEventListener(this, object: KeyboardVisibilityEventListener {
            override fun onVisibilityChanged(isOpen: Boolean) {
                if(isOpen){

                    bottomNav.setVisibility(View.INVISIBLE);

                }else{

                    bottomNav.setVisibility(View.VISIBLE);

                }
            }

        })

    }

}