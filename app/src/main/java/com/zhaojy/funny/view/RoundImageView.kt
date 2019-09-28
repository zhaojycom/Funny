package com.zhaojy.funny.view

import android.content.Context
import android.graphics.*
import android.graphics.Bitmap.createScaledBitmap
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable
import com.zhaojy.funny.R

/**
 * 自定义圆形图片
 *
 * @author: zhaojy
 * @data:On 2018/9/16.
 */
class RoundImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {
    /*半径*/
    /**
     * 获得半径
     */
    /**
     * 设置半径
     *
     * @param radius
     */
    var radius = 0f
    /**
     * 类型
     */
    private val type: Int
    /**
     * 类型-圆角
     */
    private val TYPE_FTLLET = 0

    /**
     * 类型-圆形
     */
    private val TYPE_ROUND = 1
    private val paint: Paint
    private val mWidth: Int = 0
    private val mHeight: Int = 0

    init {
        val typedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.RoundImageView, defStyleAttr, 0
        )
        radius = typedArray.getDimension(R.styleable.RoundImageView_xhradius, 0f)
        type = typedArray.getInt(R.styleable.RoundImageView_xhtype, 1)
        typedArray.recycle()
        paint = Paint()
    }

    /**
     * 绘制图片
     */
    override fun onDraw(canvas: Canvas) {
        val drawable = drawable

        if (null != drawable) {
            var bitmap: Bitmap? = null
            if (drawable is BitmapDrawable) {
                bitmap = drawable.bitmap
            } else {
                bitmap = (drawable as GlideBitmapDrawable).bitmap
            }

            bitmap = getScaleBitmap(bitmap!!, this.width.toFloat(), this.height.toFloat())
            when (type) {
                TYPE_FTLLET -> {
                    //画圆角类型
                    /*不可省略*/
                    paint.reset()
                    canvas.drawBitmap(getFilletBitmap(bitmap, radius), 0f, 0f, null)
                }
                TYPE_ROUND -> {
                    //画圆形
                    val min = Math.min(width, height)
                    // 长度如果不一致，按小的值进行压缩
                    /*不可省略*/
                    paint.reset()
                    bitmap = createScaledBitmap(bitmap, min, min, false)
                    canvas.drawBitmap(getRoundBitmap(bitmap, min.toFloat()), 0f, 0f, null)
                }
                else -> {
                }
            }
        } else {
            super.onDraw(canvas)
        }
    }

    /**
     * 获取圆角矩形图片方法
     *
     * @param radius
     * @param bitmap
     */
    fun getFilletBitmap(bitmap: Bitmap?, radius: Float): Bitmap {
        val output = Bitmap.createBitmap(
            this.width,
            this.height, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(output)
        val color = -0xbdbdbe
        val rect = Rect(0, 0, this.width, this.height)
        val rectF = RectF(rect)
        paint.isAntiAlias = true
        canvas.drawARGB(0, 0, 0, 0)
        paint.color = color
        canvas.drawRoundRect(rectF, radius, radius, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(bitmap!!, rect, rect, paint)
        return output
    }

    /**
     * 获取圆形imageView的方法
     *
     * @param bitmap
     * @param radius
     * @return
     */
    fun getRoundBitmap(bitmap: Bitmap?, radius: Float): Bitmap {
        val output = Bitmap.createBitmap(
            radius.toInt(),
            radius.toInt(), Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(output)
        paint.isAntiAlias = true
        canvas.drawCircle(radius / 2, radius / 2, radius / 2, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(bitmap!!, 0f, 0f, paint)
        return output
    }

    /**
     * 缩放bitmap到指定大小
     *
     * @param bitmap
     * @param scalex
     * @param scaley
     * @return
     */
    private fun getScaleBitmap(bitmap: Bitmap, scalex: Float, scaley: Float): Bitmap {
        val width = bitmap.width
        val height = bitmap.height
        val matrix = Matrix()
        matrix.postScale(scalex / width, scaley / height)
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }
}//没有这个方法就会出错。没有这句话就会读不到值
