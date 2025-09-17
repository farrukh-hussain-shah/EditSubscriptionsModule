package com.example.editsubscritionmodule.bottomsheets

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.editsubscritionmodule.R
import com.example.editsubscritionmodule.adapters.AppAdapter
import com.example.editsubscritionmodule.base.BaseBottomSheetDialogFragment
import com.example.editsubscritionmodule.databinding.BottomAppSelectBinding
import com.example.editsubscritionmodule.model.AppData

class BottomAppSelect :
    BaseBottomSheetDialogFragment<BottomAppSelectBinding>(BottomAppSelectBinding::inflate) {

    var onClick: ((AppData) -> Unit)? = null
    private var appData: AppData? = null
    private val adapter by lazy { AppAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listOfData = listOf(
            AppData(R.drawable.ic_netflix, "Netflix",isChecked = true),
            AppData(R.drawable.amazon, "Amazon"),
            AppData(R.drawable.apple, "Apple"),
            AppData(R.drawable.newyorktimes, "New York Times"),
            AppData(R.drawable.spotify, "Spotify"),
            AppData(R.drawable.wsj, "Wall Street Journal"),
        )

        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter
        adapter.setItems(listOfData)

        adapter.onClick = { app ->
            appData = app
        }
        binding.recyclerView.minimumHeight = 500
        binding.etSearch.doOnTextChanged { text,a,b,c ->
            val query = text.toString()
            adapter.filter { it is AppData && it.name.contains(query, ignoreCase = true) }
        }

        binding.topBar.tvEnd.setOnClickListener {
            appData?.let {
                onClick?.invoke(it)
                dismiss()
            }
        }
    }
}
