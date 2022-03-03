package com.example.heroapitask.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.heroapitask.databinding.FragmentHeroDetailsBinding

class HeroDetailFragment:Fragment() {
    private val viewModel:HeroViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater,
                          container:ViewGroup?,savedInstanceState: Bundle?):View? {

        val binding= FragmentHeroDetailsBinding.inflate(inflater)
        binding.lifecycleOwner=this
        binding.viewModel=viewModel

        return binding.root

    }
}