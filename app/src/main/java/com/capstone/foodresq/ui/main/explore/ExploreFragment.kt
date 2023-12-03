package com.capstone.foodresq.ui.main.explore

import android.R.attr.spacing
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.foodresq.data.FoodItem
import com.capstone.foodresq.data.Selection
import com.capstone.foodresq.databinding.FragmentExploreBinding
import com.capstone.foodresq.utils.GridSpacingItemDecoration


class ExploreFragment : Fragment() {

    lateinit var binding:FragmentExploreBinding
    private lateinit var search:String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentExploreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setData()

        search=""
        with(binding){
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { textView, i, keyEvent ->
                    searchBar.setText(searchView.text)
                    search=searchBar.text.toString()
                    searchView.hide()
                    false
                }
        }
    }

    private fun setData(){
        val exampleFoodItemList = listOf(
            FoodItem(1),
            FoodItem(2),
            FoodItem(3),
            FoodItem(4),
        )

        val exampleSelectionList = listOf(
            Selection(1),
            Selection(2),
            Selection(3),
            Selection(4),
            Selection(5),
            Selection(6),
        )

        val FoodItemAdapter=FoodItemAdapter(exampleFoodItemList)
        binding.rvFoodRecommendation.layoutManager=GridLayoutManager(requireActivity(),2)
        binding.rvFoodRecommendation.addItemDecoration(GridSpacingItemDecoration(2,16,false))
        binding.rvFoodRecommendation.adapter=FoodItemAdapter

        binding.rvPopularFoods.layoutManager=GridLayoutManager(requireActivity(),2)
        binding.rvPopularFoods.addItemDecoration(GridSpacingItemDecoration(2,16,false))
        binding.rvPopularFoods.adapter=FoodItemAdapter


        val SelectionAdapter=SelectionAdapter(exampleSelectionList)
        binding.rvSelections.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvSelections.adapter=SelectionAdapter
    }
}