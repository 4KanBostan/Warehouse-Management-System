package com.furkanbostan.depoyonetim.Home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.furkanbostan.depoyonetim.R
import com.furkanbostan.depoyonetim.databinding.FragmentEditProfilBinding
import com.furkanbostan.depoyonetim.databinding.FragmentEditStoreBinding
import com.furkanbostan.depoyonetim.databinding.FragmentStoreBinding


class EditStoreFragment : Fragment() {
   private var binding:FragmentEditStoreBinding?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= FragmentEditStoreBinding.inflate(layoutInflater,container,false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.backHomeButton.setOnClickListener{
            findNavController().popBackStack()
        }
    }




}