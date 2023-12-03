package com.capstone.foodresq.ui.main.order.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.capstone.foodresq.ui.main.order.HistoryOrderPagerFragment

class OrderHistoryPagerAdapter(activity:AppCompatActivity ) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }
    override fun createFragment(position: Int): Fragment {
        val fragment = HistoryOrderPagerFragment()
        fragment.arguments = Bundle().apply {
            putInt(HistoryOrderPagerFragment.PAGER_NUMBER, position+1)
        }
        return fragment
    }
}