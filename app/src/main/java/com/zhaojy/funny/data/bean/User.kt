package com.zhaojy.funny.bean

/**
 * @author: zhaojy
 * @data:On 2018/9/28.
 */

class User(phone: String, avatar: String) {
    var phone: String? = null
    var avatar: String? = null
    var banner: String? = null
    /**
     * 账户是否发生改变
     */
    var isUserChange = false

    init {
        this.phone = phone
        this.avatar = avatar
    }
}
