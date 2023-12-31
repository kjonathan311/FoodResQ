package com.capstone.foodresq.ui.map

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.foodresq.R
import com.capstone.foodresq.data.classes.FoodItem
import com.capstone.foodresq.databinding.ActivityMapsBinding
import com.capstone.foodresq.ui.detail.DetailActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, Toolbar.OnMenuItemClickListener {

    private lateinit var mMap: GoogleMap
    lateinit var binding:ActivityMapsBinding
    private val viewModel : MapViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idRestaurant = intent.getStringExtra("restaurant_id")

        viewModel.getDetailRestaurant(idRestaurant!!)
        viewModel.restaurant.observe(this){
            binding.tvMapTitle.text = it?.name
            binding.tvMapsAddress.text = it?.address
            setData(it!!.foods)
        }
        setMenuIcon()

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    fun setMenuIcon(){
        binding.toolbarMap.setNavigationIcon(R.drawable.ic_back)
        binding.toolbarMap.setNavigationOnClickListener({
            finish()
        })
        binding.toolbarMap.setOnMenuItemClickListener(this)

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_map, menu)
        return super.onCreateOptionsMenu(menu)
    }

    fun setData(listFood : List<FoodItem>){
        val FoodItemAdapter = MapFoodAdapter(listFood){
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("id", it.id)
            intent.putExtra("restaurant_id", it.restaurant_id)
            startActivity(intent)
        }

        binding.rvMap.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvMap.adapter = FoodItemAdapter
    }

    override fun onMapReady(map: GoogleMap) {
        mMap = map

        //attribute map control
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isIndoorLevelPickerEnabled = true
        mMap.uiSettings.isCompassEnabled = true
        mMap.uiSettings.isMapToolbarEnabled = true

        viewModel.restaurant.observe(this){
            if (it != null){
                val longitude = it.longitude
                val latitude = it.latitude
                if (longitude != null && latitude != null){
                    val latLng = LatLng(latitude, longitude)
                    mMap.addMarker(
                        MarkerOptions()
                            .position(latLng)
                            .title(it.name)
                            .snippet(it.address)
                    )
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
                } else {
                    val latLng = LatLng(0.0, 0.0)
                    mMap.addMarker(
                        MarkerOptions()
                            .position(latLng)
                            .title(it.name)
                            .snippet(it.address)
                    )
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
                }
            }
        }
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_show_direction -> {
                viewModel.restaurant.observe(this){
                    if (it != null){
                        var longitude = it.longitude
                        var latitude = it.latitude
                        if (longitude == null && latitude == null){
                            longitude = 0.0
                            latitude = 0.0
                        }
                        val mapUri = Uri.parse("https://maps.google.com/maps?daddr=$latitude,$longitude")
                        val intent = Intent(Intent.ACTION_VIEW, mapUri)
                        startActivity(intent)
                    }
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
