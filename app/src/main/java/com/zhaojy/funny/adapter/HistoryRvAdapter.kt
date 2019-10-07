package com.zhaojy.funny.adapter

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.zhaojy.funny.R
import com.zhaojy.funny.bean.History

/**
 *@author: zhaojy
 *@data:On 2019/10/7.
 */
class HistoryRvAdapter : BaseQuickAdapter<History, BaseViewHolder> {

    constructor(data: List<History>?) : super(R.layout.history_item, data) {

    }

    override fun convert(helper: BaseViewHolder, item: History) {
        val img: ImageView = helper.getView(R.id.img)
        Glide.with(mContext)
            .load(item.imgUrl)
            .placeholder(R.mipmap.icon)
            .into(img)
        helper.setText(R.id.sort, "[" + item.sort + "]")
        helper.setText(R.id.title, item.title)
    }
}