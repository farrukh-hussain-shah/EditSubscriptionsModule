package com.example.editsubscritionmodule.bottomsheets

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.editsubscritionmodule.R
import com.example.editsubscritionmodule.adapters.AppAdapter
import com.example.editsubscritionmodule.adapters.FrequencyAdapter
import com.example.editsubscritionmodule.base.BaseBottomSheetDialogFragment
import com.example.editsubscritionmodule.databinding.BottomAppSelectBinding
import com.example.editsubscritionmodule.model.AppData

class BottomRemindMe :
    BaseBottomSheetDialogFragment<BottomAppSelectBinding>(BottomAppSelectBinding::inflate) {
    var onClick: ((AppData) -> Unit)? = null
    private var appData: AppData? = null

    private val adapter by lazy { FrequencyAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topBar.tvCenter.text = "Remind Me"
        binding.topBar.tvEnd.setOnClickListener {
            dismiss()
        }
        val listOfData = listOf(
            AppData(R.drawable.subsrciption, "2 days before", isChecked = true),
            AppData(R.drawable.utility, "7 days before"),
            AppData(R.drawable.ic_money_2, "14 days before"),
        )
        binding.etSearch.visibility = View.GONE
        binding.recyclerView.adapter = adapter
        adapter.setItems(listOfData)
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        // item click
        adapter.onClick = { app ->
            appData = app
        }

        binding.topBar.tvEnd.setOnClickListener {
            appData?.let {
                onClick?.invoke(it)
                dismiss()
            }
        }
    }
}
