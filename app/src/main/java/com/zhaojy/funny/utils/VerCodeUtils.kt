package com.zhaojy.funny.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import java.util.*

/**
 * 图形验证码生成工具类
 *
 * @author: zhaojy
 * @data:On 2018/9/19.
 */

class VerCodeUtils {
    private var mPaddingLeft: Int = 0
    private var mPaddingTop: Int = 0
    private val mBuilder = StringBuilder()
    private val mRandom = Random()

    /**
     * 得到图片中的验证码字符串
     *
     * @return
     */
    var code: String? = null
        private set

    /**
     * 生成验证码图片  返回类型为bitmap 直接用imageview.setbitmap()即可
     *
     * @return
     */
    fun createBitmap(): Bitmap {
        //每次生成验证码图片时初始化
        mPaddingLeft = 0
        mPaddingTop = 0

        val bitmap = Bitmap.createBitmap(DEFAULT_WIDTH, DEFAULT_HEIGHT, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        code = createCode()

        canvas.drawColor(Color.rgb(255, 255, 255))
        val paint = Paint()
        paint.textSize = DEFAULT_FONT_SIZE.toFloat()

        for (i in 0 until code!!.length) {
            randomTextStyle(paint)
            randomPadding()
            canvas.drawText(code!![i] + "", mPaddingLeft.toFloat(), mPaddingTop.toFloat(), paint)
        }

        //干扰线
        for (i in 0 until DEFAULT_LINE_NUMBER) {
            drawLine(canvas, paint)
        }
        //保存
        canvas.save()
        canvas.restore()

        return bitmap
    }

    //生成验证码
    fun createCode(): String {
        //使用之前首先清空内容
        mBuilder.delete(0, mBuilder.length)

        for (i in 0 until DEFAULT_CODE_LENGTH) {
            mBuilder.append(CHARS[mRandom.nextInt(CHARS.size)])
        }

        return mBuilder.toString()
    }

    /**
     * 生成干扰线
     */
    private fun drawLine(canvas: Canvas, paint: Paint) {
        val color = randomColor()
        val startX = mRandom.nextInt(DEFAULT_WIDTH)
        val startY = mRandom.nextInt(DEFAULT_HEIGHT)
        val stopX = mRandom.nextInt(DEFAULT_WIDTH)
        val stopY = mRandom.nextInt(DEFAULT_HEIGHT)
        paint.strokeWidth = 1f
        paint.color = color
        canvas.drawLine(startX.toFloat(), startY.toFloat(), stopX.toFloat(), stopY.toFloat(), paint)
    }

    /**
     * 随机颜色
     */
    private fun randomColor(): Int {
        //使用之前首先清空内容
        mBuilder.delete(0, mBuilder.length)

        var haxString: String
        for (i in 0..2) {
            haxString = Integer.toHexString(mRandom.nextInt(0xFF))
            if (haxString.length == 1) {
                haxString = "0$haxString"
            }

            mBuilder.append(haxString)
        }

        return Color.parseColor("#5fe6b3")
        //return Color.parseColor("#" + mBuilder.toString());
    }

    /**
     * 随机文本样式
     */
    private fun randomTextStyle(paint: Paint) {
        val color = randomColor()
        paint.color = color
        //true为粗体，false为非粗体
        paint.isFakeBoldText = mRandom.nextBoolean()
        var skewX = (mRandom.nextInt(11) / 10).toFloat()
        skewX = if (mRandom.nextBoolean()) skewX else -skewX
        //float类型参数，负数表示右斜，整数左斜
        paint.textSkewX = skewX
        //        paint.setUnderlineText(true); //true为下划线，false为非下划线
        //        paint.setStrikeThruText(true); //true为删除线，false为非删除线
    }

    /**
     * 随机间距
     */
    private fun randomPadding() {
        mPaddingLeft += BASE_PADDING_LEFT + mRandom.nextInt(RANGE_PADDING_LEFT)
        mPaddingTop = BASE_PADDING_TOP + mRandom.nextInt(RANGE_PADDING_TOP)
    }

    companion object {
        private val CHARS = charArrayOf(
            'a',
            'b',
            'c',
            'd',
            'e',
            'f',
            'g',
            'h',
            'i',
            'j',
            'k',
            'l',
            'm',
            'n',
            'o',
            'p',
            'q',
            'r',
            's',
            't',
            'u',
            'v',
            'w',
            'x',
            'y',
            'z'
        )

        private var mCodeUtils: VerCodeUtils? = null

        //Default Settings
        //    private static final int DEFAULT_CODE_LENGTH = 6;//验证码的长度  这里是6位
        /*验证码的长度  这里是4位*/
        private val DEFAULT_CODE_LENGTH = 4
        //字体大小
        private val DEFAULT_FONT_SIZE = 60
        //多少条干扰线
        private val DEFAULT_LINE_NUMBER = 6
        //左边距
        private val BASE_PADDING_LEFT = 40
        //左边距范围值
        private val RANGE_PADDING_LEFT = 30
        //上边距
        private val BASE_PADDING_TOP = 70
        //上边距范围值
        private val RANGE_PADDING_TOP = 15
        //默认宽度.图片的总宽
        private val DEFAULT_WIDTH = 240
        //默认高度.图片的总高
        private val DEFAULT_HEIGHT = 120
        //默认背景颜色值
        private val DEFAULT_COLOR = 0x5fe6b3

        val instance: VerCodeUtils
            get() {
                if (mCodeUtils == null) {
                    mCodeUtils = VerCodeUtils()
                }
                return mCodeUtils!!
            }
    }
}
