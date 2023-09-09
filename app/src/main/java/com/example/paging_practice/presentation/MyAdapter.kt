package com.example.paging_practice.presentation

import ListData
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.paging_practice.databinding.ActivityMainBinding
import com.example.paging_practice.databinding.ListItemBinding

/**
 * 2023-09-09
 * pureum
 */
class MyAdapter: PagingDataAdapter<ListData, MyAdapter.MyViewHolder>(
        object : DiffUtil.ItemCallback<ListData>() {
            override fun areItemsTheSame(oldItem: ListData, newItem: ListData): Boolean {
                Log.e("TAG", "areItemsTheSame: $oldItem , $newItem", )
                return oldItem.id == newItem.id && oldItem.author == newItem.author
            }

            override fun areContentsTheSame(oldItem: ListData, newItem: ListData): Boolean {
                return oldItem.id == newItem.id && oldItem == newItem
            }

        }
    ) {
    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
        val item = getItem(position)?: return
        Log.e("TAG", "onBindViewHolder: item $item", )
        holder.onBind(item)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyAdapter.MyViewHolder {
        return MyViewHolder(binding = ListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        )
    }

    inner class MyViewHolder(private val binding: ListItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: ListData) {
            Log.e("TAG", "onBindViewHolder: item $item", )
            binding.image.setImageURI(item.url.toUri())
            binding.text.text = item.author
        }
    }
}