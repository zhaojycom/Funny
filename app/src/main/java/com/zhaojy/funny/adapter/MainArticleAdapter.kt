package com.zhaojy.funny.adapter

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.zhaojy.funny.R
import com.zhaojy.funny.data.bean.Article
import java.lang.ref.WeakReference


/**
 *@author: zhaojy
 *@data:On 2019/10/3.
 */
class MainArticleAdapter : BaseQuickAdapter<Article, BaseViewHolder> {

    constructor(data: List<Article>?) : super(R.layout.article_item, data) {

    }

    override fun convert(helper: BaseViewHolder, item: Article) {
        //将position保存在itemView的Tag中，以便点击时进行获取
        helper.itemView.setTag(helper.layoutPosition)
        helper.setText(R.id.title, item.title)
        helper.setText(R.id.articleSort, "- " + item.name + " -")
        val img = WeakReference<ImageView>(helper.getView(R.id.img))
        Glide.with(mContext)
            .load(item.imgUrl)
            .placeholder(R.mipmap.icon)
            .into(img.get()!!)
    }
}