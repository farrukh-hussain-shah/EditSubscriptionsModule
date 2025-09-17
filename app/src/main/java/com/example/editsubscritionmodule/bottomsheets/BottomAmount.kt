package com.example.editsubscritionmodule.bottomsheets

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
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
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        // Show keyboard initially
        binding.etSearch.post {
            imm.showSoftInput(binding.etSearch, InputMethodManager.SHOW_IMPLICIT)
        }

        // Adjust bottom padding based on keyboard visibility
        binding.main.viewTreeObserver.addOnGlobalLayoutListener(
            object : ViewTreeObserver.OnGlobalLayoutListener {
                private val defaultPadding = 70.dpToPx()
                private val keyboardPadding = 20.dpToPx()

                override fun onGlobalLayout() {
                    val rect = Rect()
                    binding.main.getWindowVisibleDisplayFrame(rect)
                    val screenHeight = binding.main.rootView.height
                    val keyboardHeight = screenHeight - rect.bottom

                    val padding = if (keyboardHeight > screenHeight * 0.15) keyboardPadding else defaultPadding

                    binding.main.setPadding(
                        binding.main.paddingLeft,
                        binding.main.paddingTop,
                        binding.main.paddingRight,
                        padding
                    )
                }
            }
        )

        // Dismiss keyboard on touch outside EditText
        binding.main.setOnClickListener {
            imm.hideSoftInputFromWindow(binding.etSearch.windowToken, 0)
            binding.etSearch.clearFocus()
        }

        // Handle OK/End click
        binding.topBar.tvEnd.setOnClickListener {
            val text = binding.etSearch.text
            if (!text.isNullOrEmpty()) {
                onClick?.invoke(text.toString().toDouble())
                dismiss()
            }
        }
    }
}
