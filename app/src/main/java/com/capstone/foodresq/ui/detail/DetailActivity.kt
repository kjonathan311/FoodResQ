package com.capstone.foodresq.ui.detail

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.capstone.foodresq.R
import com.capstone.foodresq.databinding.ActivityDetailBinding
import com.capstone.foodresq.ui.map.MapsActivity
import com.capstone.foodresq.utils.Utils
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding
    private val detailViewModel:DetailViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imgProduct.setImageResource(R.drawable.food_item_examp)
        binding.detailLayout.imgRestaurant.setImageResource(R.drawable.food_item_examp)

        detailViewModel.loading.observe(this) { showLoading(it) }

        val foodId=intent.getStringExtra("id")
        if(foodId!=null){
            setData(foodId)
        }
        binding.btnBack.setOnClickListener {
            finish()
        }
        binding.detailLayout.cardRestaurant.setOnClickListener {
            startActivity(Intent(this,MapsActivity::class.java))
        }
    }

    private fun showLoading(load: Boolean) {
        binding.layoutLoading.progressBar.visibility = if (load) View.VISIBLE else View.GONE
        binding.cardView.visibility = if (load) View.INVISIBLE else View.VISIBLE
        binding.scrollView2.visibility = if (load) View.INVISIBLE else View.VISIBLE
        binding.frameLayout.visibility = if (load) View.INVISIBLE else View.VISIBLE
    }
    fun setData(id:String){
        detailViewModel.getDetail(id)
        detailViewModel.foodData.observe(this){
            if(it!=null){
                findViewById<TextView>(R.id.product_name).text = it.name
                findViewById<TextView>(R.id.tv_availability).text = it.quantity.toString()+" available"
                findViewById<TextView>(R.id.price_before_discount).paintFlags= Paint.STRIKE_THRU_TEXT_FLAG
                findViewById<TextView>(R.id.price_before_discount).text = Utils.formatPrice(it.price.toString())
                findViewById<TextView>(R.id.price_after_discount).text = Utils.formatPrice(it.discount_price.toString())



                findViewById<TextView>(R.id.product_content).text = it.description

                val imageView = findViewById<ImageView>(R.id.img_product)
                Glide.with(this)
                    .load(it!!.image)
                    .transform(CenterCrop())
                    .into(imageView)
            }
        }
    }
}