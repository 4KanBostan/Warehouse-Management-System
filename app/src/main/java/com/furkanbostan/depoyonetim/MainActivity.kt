package com.furkanbostan.depoyonetim

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.furkanbostan.depoyonetim.databinding.ActivityMainBinding

import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navhostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        //navHostFragment ile bottomNavCOnttroller birleştirme işlemi
        NavigationUI.setupWithNavController(button, navhostFragment.navController)


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

        }

    }
}