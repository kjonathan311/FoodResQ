package com.capstone.foodresq.ui.main.order.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.capstone.foodresq.R
import androidx.recyclerview.widget.DiffUtil
import com.capstone.foodresq.data.classes.Order
import com.google.android.material.imageview.ShapeableImageView


class OrderAdapter(
    private val orderList: List<Order>,
    private val detailOrderClickListener: (Order)->Unit,
    private val clickListener: (Order) -> Unit
): RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {
//    ) : PagedListAdapter<Order, OrderAdapter.OrderViewHolder>(DIFF_CALLBACK){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val itemView=inflater.inflate(R.layout.order_item,parent,false)
        return OrderViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order=orderList.get(position)

        holder.bind(order)
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    inner class OrderViewHolder internal constructor(itemView: View):RecyclerView.ViewHolder(itemView){
        val title:TextView=itemView.findViewById(R.id.tv_order_title)
        val address:TextView=itemView.findViewById(R.id.tv_order_address)
        val pickup:TextView=itemView.findViewById(R.id.tv_order_pickup)
        val time:TextView=itemView.findViewById(R.id.tv_order_time)
        val btnBarcode: ImageButton =itemView.findViewById(R.id.btn_barcode)
        val imageView: ShapeableImageView = itemView.findViewById(R.id.iv_order)

        fun bind(order: Order){
            imageView.setImageResource(R.drawable.exampl_burg)
            btnBarcode.setOnClickListener {
                clickListener(order)
            }
            itemView.setOnClickListener {
                detailOrderClickListener(order)
            }
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