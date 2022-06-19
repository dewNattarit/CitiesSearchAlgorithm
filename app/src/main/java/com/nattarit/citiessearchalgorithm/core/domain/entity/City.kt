package com.nattarit.citiessearchalgorithm.core.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class City(
   var id:Long? = null,
   var country:String? = null,
   var name:String? = null,
   var coordinate:Coordinate? = null,
): Parcelable {
}