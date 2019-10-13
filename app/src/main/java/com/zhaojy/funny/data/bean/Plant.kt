package com.zhaojy.funny.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * @author: zhaojy
 * @data:On 2018/9/27.
 */

class Plant() : Parcelable {
    var id: Int = 0
    var classifyId: Int = 0
    var articleUrl: String? = null
    var imgUrl: String? = null
    var plantName: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        classifyId = parcel.readInt()
        articleUrl = parcel.readString()
        imgUrl = parcel.readString()
        plantName = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(classifyId)
        parcel.writeString(articleUrl)
        parcel.writeString(imgUrl)
        parcel.writeString(plantName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Plant> {
        override fun createFromParcel(parcel: Parcel): Plant {
            return Plant(parcel)
        }

        override fun newArray(size: Int): Array<Plant?> {
            return arrayOfNulls(size)
        }
    }
}
