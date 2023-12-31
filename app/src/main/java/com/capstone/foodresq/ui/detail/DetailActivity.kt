package com.capstone.foodresq.ui.detail

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.capstone.foodresq.R
import com.capstone.foodresq.databinding.ActivityDetailBinding
import com.capstone.foodresq.ui.detail_order.DetailOrderActivity
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
        val restaurantid = intent.getStringExtra("restaurant_id")
        if(foodId!=null){
            setData(foodId)
            counterHandler(foodId)
        }
        binding.btnBack.setOnClickListener {
            finish()
        }
        binding.detailLayout.cardRestaurant.setOnClickListener {
            detailViewModel.getDetailRestaurant(restaurantid!!)
            detailViewModel.restaurant.observe(this){
                val intent = Intent(this, MapsActivity::class.java)
                intent.putExtra("restaurant_id", it?.id)
                startActivity(intent)
            }
            startActivity(Intent(this,MapsActivity::class.java))
        }
        orderButtonHandler(foodId!!, restaurantid!!)
    }

    private fun orderButtonHandler(foodId:String, restaurantId:String) {
        val counter = binding.counter.tvCounter.text.toString().toInt()
        binding.counter.btnOrder.isClickable = counter!=0
        binding.counter.btnOrder.setOnClickListener {
            if (counter!=0){
                detailViewModel.orderFood(restaurantId, foodId, counter)
                detailViewModel.successResult.observe(this){
                    if (it!=null){
                        val intent = Intent(this, DetailOrderActivity::class.java)
                        intent.putExtra("order", it.data.id)
                        intent.putExtra("restaurantId", restaurantId)
                        startActivity(intent)
                    }
                }
                detailViewModel.errorMessage.observe(this){
                    showAlertDialog(this, "Error", it)
                }
            }
        }
    }

    private fun showAlertDialog(context: Context, title: String, message: String?) {
        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Ok") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun counterHandler(id:String) {
        var counter = binding.counter.tvCounter.text.toString().toInt()
        detailViewModel.getDetail(id)
        detailViewModel.foodData.observe(this){
            if (it!=null){
                if (it.quantity != 0){
                    binding.counter.tvCounter.text = "1"
                    binding.counter.btnMinus.isClickable = counter != 1
                    binding.counter.btnPlus.isClickable = counter < it.quantity

                    binding.counter.btnMinus.setOnClickListener {
                        counter = counter - 1
                        binding.counter.tvCounter.text = counter.toString()
                    }
                    binding.counter.btnPlus.setOnClickListener {
                        counter = counter + 1
                        binding.counter.tvCounter.text = counter.toString()
                    }
                } else {
                    binding.counter.tvCounter.text = "0"
                    binding.counter.btnMinus.isClickable = false
                    binding.counter.btnPlus.isClickable = false
                }
            }
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
                with(binding){
                    detailLayout.productName.text = it.name
                    detailLayout.tvAvailability.text = getString(R.string.txt_available, it.quantity)
                    detailLayout.priceBeforeDiscount.apply {
                        paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                        text = Utils.formatPrice(it.price.toString())
                    }
                    detailLayout.priceAfterDiscount.text = Utils.formatPrice(it.discount_price.toString())
                    detailLayout.productContent.text = it.description
                    Glide.with(this@DetailActivity)
                        .load(it.image)
                        .transform(CenterCrop())
                        .into(imgProduct)
                }
                detailViewModel.getDetailRestaurant(it.restaurant_id)
                detailViewModel.restaurant.observe(this){
                    with(binding){
                        detailLayout.tvRestaurantName.text = it!!.name
                        detailLayout.tvRestaurantAddress.text = it.address
                        detailLayout.tvPickupTime.text = resources.getString(R.string.pickup_time, it.openTime, it.closeTime)
                        detailLayout.tvRating.text = resources.getString(R.string.rating_format, it.rating)
                    }
                }
            }
        }

    }


}