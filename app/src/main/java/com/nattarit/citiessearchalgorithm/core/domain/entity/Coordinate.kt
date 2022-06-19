package com.nattarit.citiessearchalgorithm.core.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Coordinate(
var longitude:Double? = null,
var latititude:Double? = null,
): Parcelable {
}