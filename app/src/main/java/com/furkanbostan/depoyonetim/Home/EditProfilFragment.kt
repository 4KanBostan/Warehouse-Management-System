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


class EditProfilFragment : Fragment() {
    private var binding: FragmentEditProfilBinding?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding=FragmentEditProfilBinding.inflate(layoutInflater,container,false)
        val view = binding!!.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding!!.backHomeButton.setOnClickListener{
           // Navigation.findNavController(it).navigate(R.id.action_editProfilFragment_to_homeFragment)
            findNavController().popBackStack()

        }
    }


}