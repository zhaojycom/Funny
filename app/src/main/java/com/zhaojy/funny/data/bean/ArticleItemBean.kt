package com.zhaojy.funny.bean

/**
 * @author: zhaojy
 * @data:On 2018/9/20.
 */

class ArticleItemBean {
    var articleType: String? = null
        get() = if (field == null) "" else field
    var imgUrl: String? = null
        get() = if (field == null) "" else field
    var title: String? = null
        get() = if (field == null) "" else field
    var briefContent: String? = null
        get() = if (field == null) "" else field
}
