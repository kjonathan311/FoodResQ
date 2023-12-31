package com.capstone.foodresq.ui.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.capstone.foodresq.R
import com.capstone.foodresq.data.classes.FoodItem
import com.capstone.foodresq.databinding.ActivityListBinding
import com.capstone.foodresq.ui.detail.DetailActivity
import com.capstone.foodresq.ui.main.explore.FoodItemAdapter
import com.capstone.foodresq.utils.GridSpacingItemDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListActivity : AppCompatActivity() {

    lateinit var binding:ActivityListBinding
    private val listViewModel:ListViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val foodName=intent.getStringExtra("query")
        val foodList: List<FoodItem>? = intent.getParcelableArrayListExtra("list")
        if(foodName!=null){
            setData(foodName)
        }else if(foodList!=null){
            setDataList(foodList)
        }
        setMenuIcon()
    }

    fun setMenuIcon(){
        binding.toolbar.setNavigationIcon(R.drawable.ic_back)
        binding.toolbar.setNavigationOnClickListener({
            finish()
        })
    }
    fun setData(foodName:String){
        listViewModel.loading.observe(this){
            showLoading(it)
        }

        listViewModel.getFoodsByQuery(foodName)
        listViewModel.queryFoodsData.observe(this){
            if(it!=null){
                if (it.isNotEmpty()){
                    val ListAdapter=FoodItemAdapter(it){
                        startActivity(Intent(this,DetailActivity::class.java).putExtra("id",it.id))
                    }
                    binding.rvList.layoutManager=GridLayoutManager(this,2)
                    binding.rvList.addItemDecoration(GridSpacingItemDecoration(2,16,false))
                    binding.rvList.adapter=ListAdapter
                }else{
                    binding.tvNotFound.visibility = View.VISIBLE
                    binding.rvList.visibility = View.GONE
                }
            }
        }
    }
    fun setDataList(foodList:List<FoodItem>){
        showLoading(false)
        val FoodItemAdapter=FoodItemAdapter(foodList){
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("id", it.id)
            intent.putExtra("restaurant_id", it.restaurant_id)
            startActivity(intent)
        }
        binding.rvList.layoutManager=GridLayoutManager(this,2)
        binding.rvList.addItemDecoration(GridSpacingItemDecoration(2,16,false))
        binding.rvList.adapter=FoodItemAdapter
    }

    fun showLoading(load:Boolean){
        binding.progressList.visibility = if (load) View.VISIBLE else View.GONE

    }
}