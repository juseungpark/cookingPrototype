package com.juseung.myapplication


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.juseung.myapplication.databinding.ItemCookingBinding

class CookingAdapter : ListAdapter<Row, CookingAdapter.CookingViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CookingViewHolder {
        val binding = ItemCookingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CookingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CookingViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CookingViewHolder(private val binding: ItemCookingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Row) {
            with(binding) {
                tvName.text = item.rCPNM
                tvType.text = item.iNFOENG
                tvPhone.text = item.iNFOCAR
                tvAddr.text = item.iNFOPRO
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Row>() {
            override fun areItemsTheSame(oldItem: Row, newItem: Row): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun areContentsTheSame(oldItem: Row, newItem: Row): Boolean {
                return oldItem == newItem
            }
        }
    }
}
