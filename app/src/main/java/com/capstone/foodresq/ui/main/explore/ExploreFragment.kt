package com.capstone.foodresq.ui.main.explore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.capstone.foodresq.R
import com.capstone.foodresq.data.FoodItem
import com.capstone.foodresq.data.History
import com.capstone.foodresq.databinding.FragmentExploreBinding


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


        val exampleFoodItemList = listOf(
            FoodItem(1),
            FoodItem(2),
            FoodItem(3),
            FoodItem(4),
        )
        val FoodItemAdapter=FoodItemAdapter(exampleFoodItemList)
        binding.rvFoodRecommendation.layoutManager=GridLayoutManager(requireActivity(),2)
        binding.rvFoodRecommendation.adapter=FoodItemAdapter


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
}