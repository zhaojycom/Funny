package com.zhaojy.funny.adapter

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.zhaojy.funny.R
import com.zhaojy.funny.data.bean.Collect

/**
 *@author: zhaojy
 *@data:On 2019/10/6.
 */
class CollectRvAdapter : BaseQuickAdapter<Collect, BaseViewHolder> {

    constructor(data: List<Collect>?) : super(R.layout.collect_item, data) {

    }

    override fun convert(helper: BaseViewHolder, item: Collect) {
        val img: ImageView = helper.getView(R.id.img)
        Glide.with(mContext)
            .load(item.imgUrl)
            .placeholder(R.mipmap.icon)
            .into(img)
        helper.setText(R.id.sort, "[" + item.sort + "]")
        helper.setText(R.id.title, item.title)
    }
}
