package com.sample.gan.models.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterItem(
    val charId: Int,
    val name: String,
    val birthday: String,
    val occupation: List<String>,
    val img: String,
    val status: String,
    val nickname: String,
    val appearance: List<Int>,
    val portrayed: String,
    val category: String,
    val betterCallSaulAppearance: List<Int>
) : Parcelable