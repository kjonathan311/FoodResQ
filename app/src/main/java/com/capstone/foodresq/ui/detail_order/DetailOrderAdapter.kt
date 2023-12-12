package com.capstone.foodresq.ui.detail_order

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.capstone.foodresq.R
import com.capstone.foodresq.data.classes.FoodItemOrder

class DetailOrderAdapter(
    private val foodOrderList: List<FoodItemOrder>,
    ): RecyclerView.Adapter<DetailOrderAdapter.DetailOrderViewHolder>() {
//    ) : PagedListAdapter<Order, OrderAdapter.OrderViewHolder>(DIFF_CALLBACK){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailOrderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.detail_order_item, parent, false)
        return DetailOrderViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DetailOrderViewHolder, position: Int) {
        val foodOrder = foodOrderList.get(position)
        holder.bind(foodOrder)
    }

    override fun getItemCount(): Int {
        return foodOrderList.size
    }

    inner class DetailOrderViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.tv_detail_order_title)
        private val picture: ImageView = itemView.findViewById(R.id.iv_detail_order)
        private val quantity: TextView = itemView.findViewById(R.id.tv_detail_quantity)

        fun bind(item: FoodItemOrder) {
            picture.setImageResource(R.drawable.food_item_examp)
        }
    }
}