package com.zhaojy.funny.bean

/**
 * @author: zhaojy
 * @data:On 2018/9/25.
 */

class WanTuBean {
    var id: Int = 0
    private var sImgUrl: String? = null
    private var bImgUrl: String? = null
    var title: String? = null
        get() = if (field == null) "" else field
    var content: String? = null
        get() = if (field == null) "" else field

    fun getbImgUrl(): String? {
        return bImgUrl
    }

    fun setbImgUrl(bImgUrl: String) {
        this.bImgUrl = bImgUrl
    }

    fun getsImgUrl(): String? {
        return sImgUrl
    }

    fun setsImgUrl(sImgUrl: String) {
        this.sImgUrl = sImgUrl
    }

}
