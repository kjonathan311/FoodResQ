package com.capstone.foodresq.ui.map

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import androidx.recyclerview.widget.GridLayoutManager
import com.capstone.foodresq.R
import com.capstone.foodresq.data.FoodItem
import com.capstone.foodresq.databinding.ActivityMapsBinding
import com.capstone.foodresq.ui.detail.DetailActivity
import com.capstone.foodresq.ui.main.explore.ExploreFragment
import com.capstone.foodresq.ui.main.explore.FoodItemAdapter
import com.capstone.foodresq.utils.GridSpacingItemDecoration

class MapsActivity : AppCompatActivity() {

    lateinit var binding:ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setMenuIcon()
        setData()

        val MapFragment=MapsFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_map_container, MapFragment)
            commit()
        }

    }

    fun setMenuIcon(){
        binding.toolbarMap.setNavigationIcon(R.drawable.ic_back)
        binding.toolbarMap.setNavigationOnClickListener({
            finish()
        })
    }
    fun setData(){
        val exampleFoodItemList = listOf(
            FoodItem(1),
            FoodItem(2),
            FoodItem(3),
            FoodItem(4),
        )
        val FoodItemAdapter= FoodItemAdapter(exampleFoodItemList){
            startActivity(Intent(this, DetailActivity::class.java))
        }
        binding.rvMap.layoutManager= GridLayoutManager(this,2)
        binding.rvMap.addItemDecoration(GridSpacingItemDecoration(2,16,false))
        binding.rvMap.adapter=FoodItemAdapter
    }
}