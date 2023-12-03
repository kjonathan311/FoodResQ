package com.capstone.foodresq.ui.main.explore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.capstone.foodresq.R
import com.capstone.foodresq.data.FoodItem
import com.google.android.material.imageview.ShapeableImageView

class FoodItemAdapter(
    private val FoodItemList: List<FoodItem>
): RecyclerView.Adapter<FoodItemAdapter.FoodItemViewHolder>() {
//    ) : PagedListAdapter<FoodItem, FoodItemAdapter.FoodItemViewHolder>(DIFF_CALLBACK){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val itemView=inflater.inflate(R.layout.food_item,parent,false)
        return FoodItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FoodItemViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return FoodItemList.size
    }

    inner class FoodItemViewHolder internal constructor(itemView: View): RecyclerView.ViewHolder(itemView){
        private val title: TextView =itemView.findViewById(R.id.tv_food_item_title)
        private val price: TextView =itemView.findViewById(R.id.tv_food_item_price)
        private val available: TextView =itemView.findViewById(R.id.tv_food_item_available)
        val imageView: ShapeableImageView = itemView.findViewById(R.id.iv_food_item)
        fun bind(){
            imageView.setImageResource(R.drawable.food_item_examp)
        }
    }


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FoodItem>() {
            override fun areItemsTheSame(oldItem: FoodItem, newItem: FoodItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: FoodItem, newItem: FoodItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}