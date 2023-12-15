package com.capstone.foodresq.ui.main.explore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.capstone.foodresq.R
import com.capstone.foodresq.data.classes.FoodItem
import com.google.android.material.imageview.ShapeableImageView

class FoodItemAdapter(
    private val FoodItemList: List<FoodItem>,
    private val clickListener: (FoodItem) -> Unit
): RecyclerView.Adapter<FoodItemAdapter.FoodItemViewHolder>() {
//    ) : PagedListAdapter<FoodItem, FoodItemAdapter.FoodItemViewHolder>(DIFF_CALLBACK){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val itemView=inflater.inflate(R.layout.food_item,parent,false)
        return FoodItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FoodItemViewHolder, position: Int) {
        val foodItem=FoodItemList.get(position)
        holder.bind(foodItem)
    }

    private val limit = 4


    override fun getItemCount(): Int {
        return if (FoodItemList.size > limit) {
            limit
        } else {
            FoodItemList.size
        }
    }

    inner class FoodItemViewHolder internal constructor(itemView: View): RecyclerView.ViewHolder(itemView){
        private val title: TextView =itemView.findViewById(R.id.tv_food_item_title)
        private val price: TextView =itemView.findViewById(R.id.tv_food_item_price)
        private val available: TextView =itemView.findViewById(R.id.tv_food_item_available)
        val imageView: ShapeableImageView = itemView.findViewById(R.id.iv_food_item)
        fun bind(item: FoodItem){
            title.text = item.name
            price.text = item.discount_price.toString()
            available.text = item.quantity.toString() + " available"
            // Use Glide to load the image from the URI or resource ID
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