package com.capstone.foodresq.ui.main.explore

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.foodresq.data.classes.FoodItem
import com.capstone.foodresq.data.classes.Selection
import com.capstone.foodresq.databinding.FragmentExploreBinding
import com.capstone.foodresq.ui.detail.DetailActivity
import com.capstone.foodresq.ui.list.ListActivity
import com.capstone.foodresq.utils.GridSpacingItemDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel


class ExploreFragment : Fragment() {

    lateinit var binding:FragmentExploreBinding
    private lateinit var search:String
    private val exploreViewModel:ExploreViewModel by viewModel()
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

        exploreViewModel.loading.observe(viewLifecycleOwner) { showLoading(it) }


        binding.exploreSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH || (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN)) {
                // Open the search activity when the Enter key is pressed
                val intent = Intent(requireActivity(), ListActivity::class.java)
                intent.putExtra("search",binding.exploreSearch.text)
                startActivity(intent)
                return@setOnEditorActionListener true
            }
            false
        }
        binding.layoutSearch.setStartIconOnClickListener {
            val intent = Intent(requireActivity(), ListActivity::class.java)
            intent.putExtra("search",binding.exploreSearch.text)
            startActivity(intent)
        }
        setData()
    }

    private fun setData(){

        val exampleSelectionList = listOf(
            Selection(1),
            Selection(2),
            Selection(3),
            Selection(4),
            Selection(5),
            Selection(6),
        )
        exploreViewModel.getAllFoods()
        exploreViewModel.allFoodsData.observe(viewLifecycleOwner){
            if(it!=null){
                val FoodItemAdapter=FoodItemAdapter(it){
                    startActivity(Intent(requireActivity(),DetailActivity::class.java))
                }
                binding.rvFoodRecommendation.layoutManager=GridLayoutManager(requireActivity(),2)
                binding.rvFoodRecommendation.addItemDecoration(GridSpacingItemDecoration(2,16,false))
                binding.rvFoodRecommendation.adapter=FoodItemAdapter
            }
        }
//        binding.rvPopularFoods.layoutManager=GridLayoutManager(requireActivity(),2)
//        binding.rvPopularFoods.addItemDecoration(GridSpacingItemDecoration(2,16,false))
//        binding.rvPopularFoods.adapter=FoodItemAdapte

        val SelectionAdapter=SelectionAdapter(exampleSelectionList)
        binding.rvSelections.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvSelections.adapter=SelectionAdapter
    }


    private fun showLoading(load: Boolean) {
        binding.progressExplore.visibility = if (load) View.VISIBLE else View.GONE
        binding.rvFoodRecommendation.visibility = if (load) View.INVISIBLE else View.VISIBLE
    }
}