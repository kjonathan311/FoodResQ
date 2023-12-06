package com.capstone.foodresq.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.foodresq.R
import com.capstone.foodresq.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imgProduct.setImageResource(R.drawable.food_item_examp)
        binding.detailLayout.imgRestaurant.setImageResource(R.drawable.food_item_examp)
    }
}