package com.example.editsubscritionmodule.bottomsheets

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.editsubscritionmodule.R
import com.example.editsubscritionmodule.adapters.AppAdapter
import com.example.editsubscritionmodule.base.BaseBottomSheetDialogFragment
import com.example.editsubscritionmodule.databinding.BottomAppSelectBinding
import com.example.editsubscritionmodule.model.AppData

class BottomCategorySelect :
    BaseBottomSheetDialogFragment<BottomAppSelectBinding>(BottomAppSelectBinding::inflate) {
    var onClick: ((AppData) -> Unit)? = null
    private var appData: AppData? = null

    private val adapter by lazy { AppAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // sample list
        val listOfData = listOf(
            AppData(R.drawable.subsrciption, "Subscription", iconSelected = R.drawable.repeat),
            AppData(R.drawable.utility, "Utility",iconSelected = R.drawable.setting),
            AppData(R.drawable.card, "Card Payment",iconSelected = R.drawable.credit_card),
            AppData(R.drawable.ic_money_2, "Loan",iconSelected = R.drawable.ic_money,isChecked = true),
            AppData(R.drawable.rent, "Rent",iconSelected = R.drawable.home),
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
