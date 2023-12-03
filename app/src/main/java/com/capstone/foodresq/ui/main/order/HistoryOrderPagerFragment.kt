package com.capstone.foodresq.ui.main.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.foodresq.R
import com.capstone.foodresq.data.History
import com.capstone.foodresq.data.Order
import com.capstone.foodresq.databinding.FragmentHistoryOrderPagerBinding
import com.capstone.foodresq.ui.main.order.adapter.HistoryAdapter
import com.capstone.foodresq.ui.main.order.adapter.OrderAdapter

class HistoryOrderPagerFragment : Fragment() {

    lateinit var binding:FragmentHistoryOrderPagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentHistoryOrderPagerBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val index=arguments?.getInt(PAGER_NUMBER,0)

        val exampleOrderList = listOf(
            Order(1),
            Order(2),
            Order(3),
        )

        val exampleHistoryList = listOf(
            History(1),
            History(2),
            History(3),
            History(4),
        )

        if (index==1){
            //order
            val orderAdapter= OrderAdapter(exampleOrderList)
            binding.rvOrder.adapter=orderAdapter
            binding.rvOrder.layoutManager= LinearLayoutManager(requireContext())
        }else{
            //history
            val historyAdapter= HistoryAdapter(exampleHistoryList)
            binding.rvOrder.adapter=historyAdapter
            binding.rvOrder.layoutManager= LinearLayoutManager(requireContext())
        }

    }

    companion object{
        const val PAGER_NUMBER = "1"
    }

}