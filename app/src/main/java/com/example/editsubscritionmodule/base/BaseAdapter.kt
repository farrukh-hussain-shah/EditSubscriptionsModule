package com.example.editsubscritionmodule

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<T, VB : ViewBinding>(
    private val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> VB
) : RecyclerView.Adapter<BaseAdapter<T, VB>.BaseViewHolder>() {

    protected val items = mutableListOf<T>()
    private var originalItems = listOf<T>() // full list for filtering

    // 🔹 Click listener
    private var onItemClick: ((T, Int) -> Unit)? = null
    fun setOnItemClickListener(listener: (T, Int) -> Unit) {
        onItemClick = listener
    }

    inner class BaseViewHolder(val binding: VB) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(item: T) {
            val pos = adapterPosition
            if (pos != RecyclerView.NO_POSITION) {
                bind(binding, item, pos)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding = bindingInflater(LayoutInflater.from(parent.context), parent, false)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount(): Int = items.size

    // 🔹 Set items and keep a copy for filtering
    fun setItems(newItems: List<T>) {
        originalItems = newItems
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    // 🔹 Add a filter method
    fun filter(predicate: (T) -> Boolean) {
        val filtered = if (originalItems.isEmpty()) items else originalItems.filter(predicate)
        items.clear()
        items.addAll(filtered)
        notifyDataSetChanged()
    }

    fun addItem(item: T) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun removeItem(position: Int) {
        if (position in items.indices) {
            items.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    abstract fun bind(binding: VB, item: T, position: Int)
}
