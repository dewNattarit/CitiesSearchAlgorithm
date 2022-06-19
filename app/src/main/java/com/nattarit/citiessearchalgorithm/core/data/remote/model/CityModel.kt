package com.nattarit.citiessearchalgorithm.core.data.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.nattarit.citiessearchalgorithm.core.domain.entity.City
import kotlinx.android.parcel.Parcelize

@Parcelize
class CityModel(
    @SerializedName("_id")
    var id:Long? = null,
    @SerializedName("country")
    var country:String? = null,
    @SerializedName("name")
    var name:String? = null,
    @SerializedName("coord")
    var coordinate: CoordinateModel? = null,

    ): Parcelable {
        fun toCity():City{
            val city = City()
            city.id = id
            city.country = country
            city.name = name
            city.coordinate = coordinate?.toCoordinate()
            return city
        }

}