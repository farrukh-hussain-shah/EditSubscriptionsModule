package com.example.editsubscritionmodule.adapters

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.editsubscritionmodule.BaseAdapter
import com.example.editsubscritionmodule.databinding.ItemFreequencySelectBinding
import com.example.editsubscritionmodule.model.AppData

class FrequencyAdapter : BaseAdapter<AppData, ItemFreequencySelectBinding>(ItemFreequencySelectBinding::inflate) {

    private var selectedPosition = RecyclerView.NO_POSITION
    private var initialCheckedHandled = false
    var onClick: ((AppData) -> Unit)? = null

    override fun bind(binding: ItemFreequencySelectBinding, item: AppData, position: Int) {
        binding.tvName.text = item.name

        // Automatically select the first item that is checked
        if (!initialCheckedHandled && item.isChecked) {
            selectedPosition = position
            initialCheckedHandled = true
        }

        binding.ivEnd.isVisible = position == selectedPosition

        binding.root.setOnClickListener {
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
