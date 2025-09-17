package com.example.editsubscritionmodule.bottomsheets

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.doOnTextChanged
import com.example.editsubscritionmodule.base.BaseBottomSheetDialogFragment
import com.example.editsubscritionmodule.databinding.BottomAmountBinding
import com.example.editsubscritionmodule.databinding.BottomAppSelectBinding

class BottomAmount :
    BaseBottomSheetDialogFragment<BottomAmountBinding>(BottomAmountBinding::inflate) {

    var onClick: ((Double) -> Unit)? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topBar.tvCenter.text = "Amount"

        binding.etSearch.requestFocus()
        binding.etSearch.post {
            val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(binding.etSearch, InputMethodManager.SHOW_IMPLICIT)
        }
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(binding.etSearch, InputMethodManager.SHOW_IMPLICIT)

        dialog?.window?.setSoftInputMode(android.view.WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        binding.topBar.tvEnd.setOnClickListener {
            val text = binding.etSearch.text
            if (text?.isNotEmpty() == true) {
                onClick?.invoke(text.toString().toDouble())
                dismiss()
            }
        }

        binding.etSearch.setOnEditorActionListener { v, actionId, event ->
            val input = binding.etSearch.text.toString()
            if (input.isNotEmpty()) {
                onClick?.invoke(input.toDouble())
                dismiss()
            }
            true
        }
    }
}
