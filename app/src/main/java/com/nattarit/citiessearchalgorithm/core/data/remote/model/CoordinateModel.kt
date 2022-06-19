package com.nattarit.citiessearchalgorithm.core.data.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.nattarit.citiessearchalgorithm.core.domain.entity.Coordinate
import kotlinx.android.parcel.Parcelize

@Parcelize
class CoordinateModel(
    @SerializedName("lon")
    var longitude:Double? = null,
    @SerializedName("lat")
    var latititude:Double? = null,
):Parcelable{
    fun toCoordinate(): Coordinate {
        val coordinate = Coordinate()
        coordinate.longitude = longitude
        coordinate.latititude = latititude
        return coordinate
    }
}