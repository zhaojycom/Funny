package com.zhaojy.funny.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.GridView

/**
 * @author: zhaojy
 * @data:On 2018/10/9.
 */

class NoScrollGridView(context: Context, attrs: AttributeSet) : GridView(context, attrs) {

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        return if (ev.action == MotionEvent.ACTION_MOVE) {
            true
        } else super.dispatchTouchEvent(ev)
    }

}
