package com.capstone.foodresq.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.capstone.foodresq.R
import com.capstone.foodresq.databinding.ActivityMainBinding
import com.capstone.foodresq.ui.detail.DetailActivity
import com.capstone.foodresq.ui.login.LoginActivity
import com.capstone.foodresq.ui.main.explore.ExploreFragment
import com.capstone.foodresq.ui.main.order.OrderFragment
import com.capstone.foodresq.ui.main.profile.ProfileFragment
import com.capstone.foodresq.ui.register.RegisterActivity

private lateinit var binding:ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startActivity(Intent(this, DetailActivity::class.java))

        val ExploreFragment= ExploreFragment()
        val OrderFragment= OrderFragment()
        val ProfileFragment= ProfileFragment()

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