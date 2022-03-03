package com.example.heroapitask.ui

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.heroapitask.R
import com.example.heroapitask.databinding.FragmentHeroListBinding


class HeroListFragment: Fragment() {


   private val viewModel:HeroViewModel by activityViewModels {
       HeroViewModelFactory(
           (activity?.application as HeroApplication).database.dataDao()
       )
   }


    private var _binding: FragmentHeroListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHeroListBinding.inflate(inflater,container,false)

        if(isNetworkConnected()){

            allData()

        }else{

            offlineData()

        }


        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerView.adapter= HeroListAdapter( HeroListener { hero->
            viewModel.onHeroClicked(hero)
            findNavController()
                .navigate(R.id.action_heroListFragment_to_heroDetailsFragment)
        })
        return binding.root

        // Inflate the layout for this fragment

    }

    private fun allData(){

        viewModel.getHeroList()

    }

    private fun offlineData(){
        viewModel.getData()
    }


    private fun isNetworkConnected(): Boolean {
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return cm!!.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
    }

}