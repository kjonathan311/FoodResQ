package com.capstone.foodresq.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.capstone.foodresq.R
import com.capstone.foodresq.databinding.ActivityMainBinding

private lateinit var binding:ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ExploreFragment=ExploreFragment()
        val OrderFragment=OrderFragment()
        val ProfileFragment=ProfileFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container,ExploreFragment)
            commit()
        }

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_item1->setCurrentFragment(ExploreFragment)
                R.id.navigation_item2->setCurrentFragment(OrderFragment)
                R.id.navigation_item3->setCurrentFragment(ProfileFragment)
            }
            true
        }
    }
    private fun setCurrentFragment(fragment:Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container,fragment)
            commit()
        }
}