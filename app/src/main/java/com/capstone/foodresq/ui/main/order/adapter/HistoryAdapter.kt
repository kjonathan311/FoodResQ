package com.capstone.foodresq.ui.main.order.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.capstone.foodresq.R
import com.capstone.foodresq.data.classes.History
import com.capstone.foodresq.data.classes.Order
import com.google.android.material.imageview.ShapeableImageView


class HistoryAdapter(
    private val historyList: List<History>,
    private val clickListener: (History) -> Unit
): RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
//    ) : PagedListAdapter<Order, OrderAdapter.OrderViewHolder>(DIFF_CALLBACK){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val itemView=inflater.inflate(R.layout.history_item,parent,false)
        return HistoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val history=historyList.get(position)
        holder.bind(history)
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    inner class HistoryViewHolder internal constructor(itemView: View):RecyclerView.ViewHolder(itemView){
        private val title:TextView=itemView.findViewById(R.id.tv_history_title)
        private val address:TextView=itemView.findViewById(R.id.tv_history_address)
        private val pickup:TextView=itemView.findViewById(R.id.tv_history_pickup)
        private val time:TextView=itemView.findViewById(R.id.tv_history_success)
        private val btnRate:ImageButton=itemView.findViewById(R.id.btn_rate)
        val imageView: ShapeableImageView = itemView.findViewById(R.id.iv_history)
        fun bind(history: History){
            imageView.setImageResource(R.drawable.exampl_burg)
            btnRate.setOnClickListener {
                clickListener(history)
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