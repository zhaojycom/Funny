package com.zhaojy.funny.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.zhaojy.funny.R
import com.zhaojy.funny.bean.ClassifyBean

/**
 * @author: zhaojy
 * @data:On 2019/9/21.
 */
class LeftClassifyRvAdapter : BaseQuickAdapter<ClassifyBean, BaseViewHolder> {

    constructor(data: List<ClassifyBean>?) : super(R.layout.left_classify_item, data) {

    }

    override fun convert(helper: BaseViewHolder, item: ClassifyBean) {
        helper.setText(R.id.classifyName, item.name)
        helper.setVisible(R.id.leftBorder, item.selected)
        if (item.selected) {
            helper.setBackgroundColor(R.id.wrap, mContext.resources.getColor(R.color.white))
        } else {
            helper.setBackgroundColor(R.id.wrap, mContext.resources.getColor(R.color.themeBk))
        }
    }
}
