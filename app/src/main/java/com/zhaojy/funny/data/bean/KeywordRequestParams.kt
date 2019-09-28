package com.zhaojy.funny.bean

/**
 * @author: zhaojy
 * @data:On 2018/10/9.
 */

class KeywordRequestParams {
    var keyword: String? = null
        get() = if (field == null) "" else field
    var offset: Int = 0
    var limit: Int = 0
}
