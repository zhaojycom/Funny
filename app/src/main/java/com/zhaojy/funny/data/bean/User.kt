package com.zhaojy.funny.data.bean

/**
 * @author: zhaojy
 * @data:On 2018/9/28.
 */

class User(phone: String, avatar: String, banner: String) {
    private var phone: String? = null
    private var avatar: String? = null
    private var banner: String? = null

    init {
        this.phone = phone
        this.avatar = avatar
        this.banner = banner
    }
}
