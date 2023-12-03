package com.capstone.foodresq.ui.main.explore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.capstone.foodresq.R
import com.capstone.foodresq.data.Selection

class SelectionAdapter(
    private val SelectionList: List<Selection>
): RecyclerView.Adapter<SelectionAdapter.SelectionViewHolder>() {
//    ) : PagedListAdapter<Selection, SelectionAdapter.SelectionViewHolder>(DIFF_CALLBACK){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectionViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val itemView=inflater.inflate(R.layout.selection_item,parent,false)
        return SelectionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SelectionViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return SelectionList.size
    }

    inner class SelectionViewHolder internal constructor(itemView: View): RecyclerView.ViewHolder(itemView){
        private val title: TextView =itemView.findViewById(R.id.tv_selection)
        val imageView: ImageView = itemView.findViewById(R.id.iv_selection)
        fun bind(){
            imageView.setImageResource(R.drawable.ic_selection)
        }
    }


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Selection>() {
            override fun areItemsTheSame(oldItem: Selection, newItem: Selection): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Selection, newItem: Selection): Boolean {
                return oldItem == newItem
            }
        }
    }
}