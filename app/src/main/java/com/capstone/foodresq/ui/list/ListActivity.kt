package com.capstone.foodresq.ui.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.capstone.foodresq.R
import com.capstone.foodresq.data.classes.FoodItem
import com.capstone.foodresq.databinding.ActivityListBinding
import com.capstone.foodresq.ui.detail.DetailActivity
import com.capstone.foodresq.ui.main.explore.FoodItemAdapter
import com.capstone.foodresq.utils.GridSpacingItemDecoration

class ListActivity : AppCompatActivity() {

    lateinit var binding:ActivityListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setMenuIcon()
        setData()
    }

    fun setMenuIcon(){
        binding.toolbar.setNavigationIcon(R.drawable.ic_back)
        binding.toolbar.setNavigationOnClickListener({
            finish()
        })
    }
    fun setData(){
//        val exampleFoodItemList = listOf(
//            FoodItem(1),
//            FoodItem(2),
//            FoodItem(3),
//            FoodItem(4),
//        )
//        val FoodItemAdapter= FoodItemAdapter(exampleFoodItemList){
//            startActivity(Intent(this,DetailActivity::class.java))
//        }
//        binding.rvList.layoutManager= GridLayoutManager(this,2)
//        binding.rvList.addItemDecoration(GridSpacingItemDecoration(2,16,false))
//        binding.rvList.adapter=FoodItemAdapter
    }
}