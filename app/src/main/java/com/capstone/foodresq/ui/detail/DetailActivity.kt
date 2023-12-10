package com.capstone.foodresq.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.foodresq.R
import com.capstone.foodresq.databinding.ActivityDetailBinding
import com.capstone.foodresq.ui.map.MapsActivity

class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imgProduct.setImageResource(R.drawable.food_item_examp)
        binding.detailLayout.imgRestaurant.setImageResource(R.drawable.food_item_examp)

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.detailLayout.cardRestaurant.setOnClickListener {
            startActivity(Intent(this,MapsActivity::class.java))
        }
    }
}