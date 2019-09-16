package com.zhaojy.funny.view

import android.content.Context
import android.util.AttributeSet
import android.widget.ScrollView

/**
 * @author: zhaojy
 * @data:On 2018/1/23.
 */

class ObservableScrollView : ScrollView {
    private var onScollChangedListener: OnScollChangedListener? = null
    protected var x: Int = 0
    protected var y: Int = 0
    protected var oldx: Int = 0
    protected var oldy: Int = 0

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(
        context
    ) {
    }

    constructor(
        context: Context, attrs: AttributeSet,
        defStyle: Int
    ) : super(context, attrs, defStyle) {
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    fun setOnScollChangedListener(onScollChangedListener: OnScollChangedListener) {
        this.onScollChangedListener = onScollChangedListener
    }

    override fun onScrollChanged(x: Int, y: Int, oldx: Int, oldy: Int) {
        super.onScrollChanged(x, y, oldx, oldy)
        this.x = x
        this.y = y
        this.oldx = oldx
        this.oldy = oldy
        if (onScollChangedListener != null) {
            onScollChangedListener!!.onScrollChanged(this, x, y, oldx, oldy)
        }

        //scrollview的起始点+总高度==crollView的computeVerticalScrollRange
        if (scrollY + height == computeVerticalScrollRange()) {
            if (onScollChangedListener != null) {
                onScollChangedListener!!.onScrollToFooter(this, x, y, oldx, oldy)
            }
        }

    }

    interface OnScollChangedListener {
        fun onScrollChanged(scrollView: ObservableScrollView, x: Int, y: Int, oldx: Int, oldy: Int)

        fun onScrollToFooter(scrollView: ObservableScrollView, x: Int, y: Int, oldx: Int, oldy: Int)
    }

}