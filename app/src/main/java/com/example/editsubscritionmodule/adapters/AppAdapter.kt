package com.example.editsubscritionmodule.adapters

import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.editsubscritionmodule.BaseAdapter
import com.example.editsubscritionmodule.databinding.ItemAppSelectBinding
import com.example.editsubscritionmodule.model.AppData

class AppAdapter : BaseAdapter<AppData, ItemAppSelectBinding>(ItemAppSelectBinding::inflate) {

    var onClick: ((AppData) -> Unit)? = null
    private var selectedPosition = RecyclerView.NO_POSITION
    private var initialCheckedHandled = false

    override fun bind(binding: ItemAppSelectBinding, item: AppData, position: Int) {
        binding.ivStart.setImageResource(item.icon)
        binding.tvName.text = item.name

        // Handle initial checked item automatically
        if (!initialCheckedHandled && item.isChecked) {
            selectedPosition = position
            initialCheckedHandled = true
        }

        binding.ivEnd.isVisible = position == selectedPosition

        binding.main.setOnClickListener {
            if (selectedPosition != position) {
                val oldPos = selectedPosition
                selectedPosition = position

                if (oldPos != RecyclerView.NO_POSITION) notifyItemChanged(oldPos)
                notifyItemChanged(selectedPosition)

                onClick?.invoke(item)
            }
        }
    }
}
