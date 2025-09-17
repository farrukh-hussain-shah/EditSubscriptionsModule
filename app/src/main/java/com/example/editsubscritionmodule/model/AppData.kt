package com.example.editsubscritionmodule.model

import androidx.annotation.DrawableRes

data class AppData(
    @DrawableRes val icon: Int,
    val name: String,
    var isChecked: Boolean = false,
    @DrawableRes val iconSelected: Int? = null, )
