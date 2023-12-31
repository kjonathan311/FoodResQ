package com.capstone.foodresq.ui.map

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.capstone.foodresq.R
import com.capstone.foodresq.data.classes.FoodItem
import com.capstone.foodresq.utils.Utils
import com.google.android.material.imageview.ShapeableImageView

class MapFoodAdapter(
    private val FoodMapList : List<FoodItem>,
    private val clickListener : (FoodItem) -> Unit
) : RecyclerView.Adapter<MapFoodAdapter.MapViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MapViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.map_food_item, parent, false)
        return MapViewHolder(view)
    }

    override fun onBindViewHolder(holder: MapViewHolder, position: Int) {
        val mapFoodItem = FoodMapList.get(position)
        holder.bind(mapFoodItem)
    }

    override fun getItemCount(): Int {
        return FoodMapList.size
    }

    inner class MapViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val title: TextView =itemView.findViewById(R.id.tv_food_item_title)
        private val price: TextView =itemView.findViewById(R.id.tv_food_item_price)
        private val available: TextView =itemView.findViewById(R.id.tv_food_item_available)
        val imageView: ShapeableImageView = itemView.findViewById(R.id.iv_food_item)

        fun bind(item: FoodItem){
            title.text = item.name
            price.text = Utils.formatPrice(item.discount_price.toString())
            available.text = itemView.context.resources.getString(R.string.txt_available, item.quantity)
            Glide.with(itemView.context)
                .load(item.image)
                .transform(CenterCrop())
                .into(imageView)
            itemView.setOnClickListener {
                clickListener(item)
            }
        }
    }
}