package com.capstone.foodresq.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.capstone.foodresq.R
import com.capstone.foodresq.databinding.ActivityMainBinding
import com.capstone.foodresq.ui.detail.DetailActivity
import com.capstone.foodresq.ui.login.LoginActivity
import com.capstone.foodresq.ui.main.explore.ExploreFragment
import com.capstone.foodresq.ui.main.order.OrderFragment
import com.capstone.foodresq.ui.main.profile.ProfileFragment
import com.capstone.foodresq.ui.register.RegisterActivity
import com.capstone.foodresq.utils.Utils.observeOnce
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

private lateinit var binding:ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val mainViewModel:MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("activity created","main")
        showLoading(true)
        mainViewModel.getSession().observeOnce{
            if(it.isLogin){
                showLoading(false)
                Log.d("current Token",it.token)
            }else{
                startActivity(Intent(this,LoginActivity::class.java))
                finish()
            }
        }

        val ExploreFragment= ExploreFragment()
        val OrderFragment= OrderFragment()
        val ProfileFragment= ProfileFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container,ExploreFragment)
            addToBackStack(null)
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
            addToBackStack(null)
            commit()
        }

    private fun showLoading(load : Boolean){
        binding.layoutLoading.progressBar.isVisible = load == true
        if(load){
            binding.bottomNavigation.visibility= View.INVISIBLE
            binding.fragmentContainer.visibility= View.INVISIBLE
        }else{
            binding.bottomNavigation.visibility= View.VISIBLE
            binding.fragmentContainer.visibility= View.VISIBLE
        }
    }
}
