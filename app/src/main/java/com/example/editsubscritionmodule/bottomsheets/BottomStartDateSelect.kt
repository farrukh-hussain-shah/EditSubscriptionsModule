package com.example.editsubscritionmodule.bottomsheets

import android.os.Bundle
import android.view.View
import com.example.editsubscritionmodule.base.BaseBottomSheetDialogFragment
import com.example.editsubscritionmodule.databinding.BottomStartDateBinding
import java.text.SimpleDateFormat
import java.util.*

class BottomStartDateSelect :
    BaseBottomSheetDialogFragment<BottomStartDateBinding>(BottomStartDateBinding::inflate) {

    var onDateSelected: ((String) -> Unit)? = null
    private var selectedDate: Long = System.currentTimeMillis()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topBar.tvCenter.text = "Start Date"

        // calendar selection
        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val calendar = Calendar.getInstance().apply {
                set(year, month, dayOfMonth)
            }
            selectedDate = calendar.timeInMillis
        }

        // done action
        binding.topBar.tvEnd.setOnClickListener {
            val formatted = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
                .format(Date(selectedDate))
            onDateSelected?.invoke(formatted)
            dismiss()
        }
    }
}
