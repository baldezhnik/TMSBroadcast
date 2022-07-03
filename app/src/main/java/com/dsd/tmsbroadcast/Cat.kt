package com.dsd.tmsbroadcast

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Cat(val name: String, var hunger: Int = 50, var thirst: Int = 50, var caress: Int = 50): Parcelable {
}