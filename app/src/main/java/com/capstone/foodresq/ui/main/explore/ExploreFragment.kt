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

        search()
        setData()
    }

    private fun search(){
        binding.exploreSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH || (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN)) {
                val intent = Intent(requireActivity(), ListActivity::class.java)
                intent.putExtra("query",binding.exploreSearch.text.toString())
                startActivity(intent)
                return@setOnEditorActionListener true
            }
            false
        }
        binding.exploreSearch.setOnKeyListener { _, keyCode, event ->
            if ((event.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                val intent = Intent(requireActivity(), ListActivity::class.java)
                intent.putExtra("query",binding.exploreSearch.text.toString())
                startActivity(intent)
                return@setOnKeyListener true
            }
            false
        }
        binding.layoutSearch.setStartIconOnClickListener {
            val intent = Intent(requireActivity(), ListActivity::class.java)
            intent.putExtra("query",binding.exploreSearch.text.toString())
            startActivity(intent)
        }
    }

    private fun setData(){
        exploreViewModel.getAllFoods()
        exploreViewModel.allFoodsData.observe(viewLifecycleOwner){list->
            if(list!=null){
                val FoodItemAdapter=FoodItemAdapter(list){
                    val intent = Intent(requireActivity(), DetailActivity::class.java)
                    intent.putExtra("id", it.id)
                    intent.putExtra("restaurant_id", it.restaurant_id)
                    startActivity(intent)
                }
                binding.rvSelections.layoutManager=GridLayoutManager(requireActivity(),2)
                binding.rvSelections.addItemDecoration(GridSpacingItemDecoration(2,16,false))
                binding.rvSelections.adapter=FoodItemAdapter

                binding.tvSeemoreFoodselection.setOnClickListener {view->
                    val intent = Intent(requireActivity(), ListActivity::class.java)
                    intent.putParcelableArrayListExtra("list",ArrayList(list))//list is list<FoodItem>
                    startActivity(intent)
                }
            }
        }
        exploreViewModel.getFoodRecommendation()
        exploreViewModel.foodRecommendation.observe(viewLifecycleOwner){recommendationList->
            if (recommendationList!=null){
                val FoodItemAdapter=FoodItemAdapter(recommendationList){
                    val intent = Intent(requireActivity(), DetailActivity::class.java)
                    intent.putExtra("id", it.id)
                    intent.putExtra("restaurant_id", it.restaurant_id)
                    startActivity(intent)
                }
                binding.rvFoodRecommendation.layoutManager=GridLayoutManager(requireActivity(), 2)
                binding.rvFoodRecommendation.addItemDecoration(GridSpacingItemDecoration(2, 16, false))
                binding.rvFoodRecommendation.adapter=FoodItemAdapter

                binding.tvSeemoreRecomendations.setOnClickListener { view ->
                    val intent = Intent(requireActivity(), ListActivity::class.java)
                    intent.putParcelableArrayListExtra("list", ArrayList(recommendationList))
                    startActivity(intent)
                }
            }
        }
        exploreViewModel.getFoodPopular()
        exploreViewModel.foodPopular.observe(viewLifecycleOwner){popularList ->
            if (popularList!=null){
                val FoodItemAdapter=FoodItemAdapter(popularList){
                    val intent = Intent(requireActivity(), DetailActivity::class.java)
                    intent.putExtra("id", it.id)
                    intent.putExtra("restaurant_id", it.restaurant_id)
                    startActivity(intent)
                }
                binding.rvPopularFoods.layoutManager=GridLayoutManager(requireActivity(), 2)
                binding.rvPopularFoods.addItemDecoration(GridSpacingItemDecoration(2, 16, false))
                binding.rvPopularFoods.adapter=FoodItemAdapter

                binding.tvSeemorePopularFoods.setOnClickListener { view ->
                    val intent = Intent(requireActivity(), ListActivity::class.java)
                    intent.putParcelableArrayListExtra("list", ArrayList(popularList))
                    startActivity(intent)
                }
            }
        }
    }



    private fun showLoading(load: Boolean) {
        binding.progressExplore.visibility = if (load) View.VISIBLE else View.GONE
        binding.rvSelections.visibility = if (load) View.INVISIBLE else View.VISIBLE
    }
}