package com.furkanbostan.depoyonetim.Home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.furkanbostan.depoyonetim.R
import com.furkanbostan.depoyonetim.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        val view = binding!!.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding!!.imageViewProfilSetting.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_editProfilFragment)
        }
        binding!!.imageViewStoreSetting.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_editStoreFragment)
        }

    }


}