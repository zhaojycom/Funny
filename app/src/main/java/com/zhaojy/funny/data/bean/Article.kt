package com.zhaojy.funny.data.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * @author: zhaojy
 * @data:On 2018/10/7.
 */

class Article() : Parcelable {
    var id: Int = 0
    var articleUrl: String? = null
    var imgUrl: String? = null
    var title: String? = null
    var name: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        articleUrl = parcel.readString()
        imgUrl = parcel.readString()
        title = parcel.readString()
        name = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(articleUrl)
        parcel.writeString(imgUrl)
        parcel.writeString(title)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Article> {
        override fun createFromParcel(parcel: Parcel): Article {
            return Article(parcel)
        }

        override fun newArray(size: Int): Array<Article?> {
            return arrayOfNulls(size)
        }
    }
}
