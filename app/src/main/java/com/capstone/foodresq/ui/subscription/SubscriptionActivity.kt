package com.capstone.foodresq.ui.subscription

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.foodresq.R
import com.capstone.foodresq.databinding.ActivitySubscriptionBinding

class SubscriptionActivity : AppCompatActivity() {

    lateinit var binding:ActivitySubscriptionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySubscriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setMenuIcon()
    }

    fun setMenuIcon(){
        binding.toolbarSub.setNavigationIcon(R.drawable.ic_back)
        binding.toolbarSub.setNavigationOnClickListener({
            finish()
        })
    }
}