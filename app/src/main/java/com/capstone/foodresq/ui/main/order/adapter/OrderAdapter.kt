package com.capstone.foodresq.ui.main.order.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.capstone.foodresq.R
import androidx.recyclerview.widget.DiffUtil
import com.capstone.foodresq.data.Order
import com.google.android.material.imageview.ShapeableImageView


class OrderAdapter(
    private val orderList: List<Order>
): RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {
//    ) : PagedListAdapter<Order, OrderAdapter.OrderViewHolder>(DIFF_CALLBACK){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val itemView=inflater.inflate(R.layout.order_item,parent,false)
        return OrderViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    inner class OrderViewHolder internal constructor(itemView: View):RecyclerView.ViewHolder(itemView){
        private val title:TextView=itemView.findViewById(R.id.tv_order_title)
        private val address:TextView=itemView.findViewById(R.id.tv_order_address)
        private val pickup:TextView=itemView.findViewById(R.id.tv_order_pickup)
        private val time:TextView=itemView.findViewById(R.id.tv_order_time)
        val imageView: ShapeableImageView = itemView.findViewById(R.id.iv_order)
        fun bind(){
            imageView.setImageResource(R.drawable.exampl_burg)
        }
    }


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Order>() {
            override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
                return oldItem == newItem
            }
        }
    }
}