package com.zhaojy.funny.adapter

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.zhaojy.funny.R
import com.zhaojy.funny.bean.Plant
import java.lang.ref.WeakReference

/**
 *@author: zhaojy
 *@data:On 2019/9/22.
 */
class ClassifyPlantGridRvAdapter : BaseQuickAdapter<Plant, BaseViewHolder> {

    constructor(data: List<Plant>?) : super(R.layout.plant_item, data) {

    }

    override fun convert(helper: BaseViewHolder, item: Plant) {
        helper.setText(R.id.plantName, item.plantName)
        var plantImg: ImageView = helper.getView(R.id.plantImg)
        var weakPlantImg = WeakReference(plantImg)
        Glide.with(mContext).load(item.imgUrl).into(weakPlantImg.get())
    }
}