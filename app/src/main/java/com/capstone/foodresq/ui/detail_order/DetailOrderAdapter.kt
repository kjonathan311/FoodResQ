package com.capstone.foodresq.ui.detail_order

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.capstone.foodresq.R
import com.capstone.foodresq.data.classes.FoodItem
import com.capstone.foodresq.data.remote.response.OrderDetail

class DetailOrderAdapter(
    private val foodOrderList: List<OrderDetail>,
    private val detailFood : FoodItem
    ): RecyclerView.Adapter<DetailOrderAdapter.DetailOrderViewHolder>() {
//    ) : PagedListAdapter<Order, OrderAdapter.OrderViewHolder>(DIFF_CALLBACK){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailOrderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.detail_order_item, parent, false)
        return DetailOrderViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DetailOrderViewHolder, position: Int) {
        val foodOrder = foodOrderList.get(position)
        holder.bind(foodOrder, detailFood)
    }

    override fun getItemCount(): Int {
        return foodOrderList.size
    }

    inner class DetailOrderViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.tv_detail_order_title)
        private val picture: ImageView = itemView.findViewById(R.id.iv_detail_order)
        private val quantity: TextView = itemView.findViewById(R.id.tv_detail_quantity)

        fun bind(item: OrderDetail, foodDetail : FoodItem) {
            Glide.with(itemView)
                .load(foodDetail.image)
                .transform(CenterCrop())
                .into(picture)

            title.text = foodDetail.name
            quantity.text = itemView.context.getString(R.string.quantityDetail, item.quantity)
        }
    }
}