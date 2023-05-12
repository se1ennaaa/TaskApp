package com.example.appleadd.ui.onBoard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.appleadd.R
import com.example.appleadd.data.Pref
import com.example.appleadd.databinding.FragmentOnBoardingBinding
import com.example.appleadd.model.OnBoard
import com.example.appleadd.ui.onBoard.adapter.OnBoardingAdapter

class OnBoardingFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardingBinding
    private lateinit var pref: Pref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = Pref(requireContext())
        val adapter = OnBoardingAdapter(this::onClick)
        binding.viewPager.adapter = adapter

        binding.indicator.setViewPager(binding.viewPager)
        adapter.registerAdapterDataObserver(binding.indicator.adapterDataObserver)
    }

    private fun onClick(onBoard: OnBoard) {
        pref.saveSeen()
        findNavController().navigate(R.id.navigation_home)
    }


}

