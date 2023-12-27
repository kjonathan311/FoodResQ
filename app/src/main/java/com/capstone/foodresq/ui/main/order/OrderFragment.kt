package com.capstone.foodresq.ui.main.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.capstone.foodresq.databinding.FragmentOrderBinding
import com.capstone.foodresq.ui.main.order.adapter.OrderHistoryPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator


class OrderFragment : Fragment() {

    lateinit var binding:FragmentOrderBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentOrderBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vpOrder.adapter=OrderHistoryPagerAdapter(requireActivity() as AppCompatActivity)

        TabLayoutMediator(binding.orderTab, binding.vpOrder) { tab, position ->
            tab.text = when (position) {
                0 -> "Orders"
                1 -> "History"
                else -> ""
            }
        }.attach()
    }

}