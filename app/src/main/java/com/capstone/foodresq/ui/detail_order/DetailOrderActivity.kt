package com.capstone.foodresq.ui.detail_order

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.foodresq.R
import com.capstone.foodresq.data.classes.FoodItem
import com.capstone.foodresq.databinding.ActivityDetailOrderBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailOrderActivity : AppCompatActivity() {

    lateinit var binding:ActivityDetailOrderBinding
    private val viewModel : DetailOrderViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setMenuIcon()

        val orderId = intent.getStringExtra("order")
        val restaurantId = intent.getStringExtra("restaurantId")
        viewModel.getDetailOrder(orderId!!)
        viewModel.detailOrder.observe(this){list ->
            if (list!=null){
                val foodId = list[0].foodId
                viewModel.getRestaurantId(foodId)
                viewModel.foodDetail.observe(this){
                    val FoodOrderAdapter = DetailOrderAdapter(
                        list,
                        FoodItem(
                            it!!.id,
                            it.name,
                            it.description,
                            it.price,
                            it.discount_price,
                            it.quantity,
                            it.image,
                            it.restaurant_id
                        )
                    )
                    binding.rvFoodOrder.layoutManager = LinearLayoutManager(this)
                    binding.rvFoodOrder.adapter = FoodOrderAdapter
                    val quantity = list[0].quantity
                    val total_price = (quantity*(it.discount_price))/1000
                    binding.tvDetailOrderTotal.text = resources.getString(R.string.format_total_order, total_price)
                }
            }
        }

        viewModel.getDetailRestaurant(restaurantId!!)
        viewModel.restaurant.observe(this){restaurant ->
            if (restaurant!=null){
                with(binding){
                    tvRestaurantName.text = restaurant.name
                    tvRestaurantAddress.text = restaurant.address
                    tvRating.text = resources.getString(R.string.rating_format, restaurant.rating)
                }
            }
        }
    }

    fun setMenuIcon(){
        binding.toolbarDetailOrder.setNavigationIcon(R.drawable.ic_back)
        binding.toolbarDetailOrder.setNavigationOnClickListener({
            finish()
        })
    }
}