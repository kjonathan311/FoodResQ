package com.capstone.foodresq.ui.map

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.foodresq.R
import com.capstone.foodresq.data.dummy.dummyData
import com.capstone.foodresq.databinding.ActivityMapsBinding
import com.capstone.foodresq.ui.detail.DetailActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    lateinit var binding:ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setMenuIcon()
        setData()

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    fun setMenuIcon(){
        binding.toolbarMap.setNavigationIcon(R.drawable.ic_back)
        binding.toolbarMap.setNavigationOnClickListener({
            finish()
        })
    }

    //dummyData
    fun setData(){
        val FoodItemAdapter = MapFoodAdapter(dummyData.dummyFood){

        }

        binding.rvMap.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvMap.adapter = FoodItemAdapter
//        val exampleFoodItemList = listOf(
//            FoodItem(1),
//            FoodItem(2),
//            FoodItem(3),
//            FoodItem(4),
//        )
//        val FoodItemAdapter= FoodItemAdapter(exampleFoodItemList){
//            startActivity(Intent(this, DetailActivity::class.java))
//        }
//        binding.rvMap.layoutManager= GridLayoutManager(this,2)
//        binding.rvMap.addItemDecoration(GridSpacingItemDecoration(2,16,false))
//        binding.rvMap.adapter=FoodItemAdapter
    }

    //dummyData
    override fun onMapReady(map: GoogleMap) {
        mMap = map

        //attribute map control
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isIndoorLevelPickerEnabled = true
        mMap.uiSettings.isCompassEnabled = true
        mMap.uiSettings.isMapToolbarEnabled = true

        val latLng = LatLng(-6.227974587871831, 107.00071847935932)
        mMap.addMarker(
            MarkerOptions()
                .position(latLng)
                .title("Gourmets Eats Summarecon")
                .snippet("Jl. Boulevard Selatan Mall Summarecon Lt.2")
        )
    }
}