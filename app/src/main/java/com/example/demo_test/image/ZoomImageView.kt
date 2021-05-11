package com.example.demo_test.image

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.example.demo_test.utils.mLog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.abs
import kotlin.math.sqrt


/**
 * @Author: Nisikki
 * @Date: 2021/5/8
 * @Describe:
 */
class ZoomImageView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    /**
     * 用于对图片进行移动和缩放变换的矩阵
     */
    private var mMatrix: Matrix = Matrix()

    /**
     * 待展示的Bitmap对象
     */
    private  var sourceBitmap: Bitmap ?= null

    /**
     * 记录当前操作的状态，可选值为STATUS_INIT、STATUS_ZOOM_OUT、STATUS_ZOOM_IN和STATUS_MOVE
     */
    private var currentStatus: Int

    /**
     * ZoomImageView控件的宽度
     */
    private var mWidth = 0

    /**
     * ZoomImageView控件的高度
     */
    private var mHeight = 0

    /**
     * 记录两指同时放在屏幕上时，中心点的横坐标值
     */
    private var centerPointX = 0f

    /**
     * 记录两指同时放在屏幕上时，中心点的纵坐标值
     */
    private var centerPointY = 0f

    /**
     * 记录当前图片的宽度，图片被缩放时，这个值会一起变动
     */
    private var currentBitmapWidth = 0f

    /**
     * 记录当前图片的高度，图片被缩放时，这个值会一起变动
     */
    private var currentBitmapHeight = 0f

    /**
     * 记录上次手指移动时的横坐标
     */
    private var lastXMove = -1f

    /**
     * 记录上次手指移动时的纵坐标
     */
    private var lastYMove = -1f

    /**
     * 记录手指在横坐标方向上的移动距离
     */
    private var movedDistanceX = 0f

    /**
     * 记录手指在纵坐标方向上的移动距离
     */
    private var movedDistanceY = 0f

    /**
     * 记录图片在矩阵上的横向偏移值
     */
    private var totalTranslateX = 0f

    /**
     * 记录图片在矩阵上的纵向偏移值
     */
    private var totalTranslateY = 0f

    /**
     * 记录图片在矩阵上的总缩放比例
     */
    private var totalRatio = 0f

    /**
     * 记录手指移动的距离所造成的缩放比例
     */
    private var scaledRatio = 0f

    /**
     * 记录图片初始化时的缩放比例
     */
    private var initRatio = 0f

    /**
     * 记录上次两指之间的距离
     */
    private var lastFingerDis = 0.0

    /**
     * 将待展示的图片设置进来。
     *
     * @param bitmap
     * 待展示的Bitmap对象
     */
    fun setImageBitmap(bitmap: Bitmap) {
        sourceBitmap = bitmap
        invalidate()
    }

    fun urlToBitmap(url:String){
       GlobalScope.launch {
           val bitmap = withContext(Dispatchers.IO){
               Glide.with(context)
                   .asBitmap()
                   .load(url)
                   .submit()
                   .get()
           }
           sourceBitmap = bitmap
           invalidate()
       }
        mLog.d("ZoomImageView", "加载:$url")

    }
     override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        if (changed) {
            // 分别获取到ZoomImageView的宽度和高度
            mWidth = getWidth()
            mHeight = getHeight()
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_POINTER_DOWN -> if (event.pointerCount == 2) {
                // 当有两个手指按在屏幕上时，计算两指之间的距离
                lastFingerDis = distanceBetweenFingers(event)
            }
            MotionEvent.ACTION_MOVE ->/* if (event.pointerCount == 1) {
                // 只有单指按在屏幕上移动时，为拖动状态
                val xMove = event.x
                val yMove = event.y
                if (lastXMove == -1f && lastYMove == -1f) {
                    lastXMove = xMove
                    lastYMove = yMove
                }
                currentStatus = STATUS_MOVE
                movedDistanceX = xMove - lastXMove
                movedDistanceY = yMove - lastYMove
                // 进行边界检查，不允许将图片拖出边界
                if (totalTranslateX + movedDistanceX > 0) {
                    movedDistanceX = 0f
                } else if (mWidth - (totalTranslateX + movedDistanceX) > currentBitmapWidth) {
                    movedDistanceX = 0f
                }
                if (totalTranslateY + movedDistanceY > 0) {
                    movedDistanceY = 0f
                } else if (mHeight - (totalTranslateY + movedDistanceY) > currentBitmapHeight) {
                    movedDistanceY = 0f
                }
                // 调用onDraw()方法绘制图片
                invalidate()
                lastXMove = xMove
                lastYMove = yMove
            } else*/ if (event.pointerCount == 2) {
                // 有两个手指按在屏幕上移动时，为缩放状态
                centerPointBetweenFingers(event)
                val fingerDis = distanceBetweenFingers(event)
                currentStatus = if (fingerDis > lastFingerDis) {
                    STATUS_ZOOM_OUT
                } else {
                    STATUS_ZOOM_IN
                }
                // 进行缩放倍数检查，最大只允许将图片放大4倍，最小可以缩小到初始化比例
                if (currentStatus == STATUS_ZOOM_OUT && totalRatio < 4 * initRatio
                    || currentStatus == STATUS_ZOOM_IN && totalRatio > initRatio
                ) {
                    scaledRatio = (fingerDis / lastFingerDis).toFloat()
                    totalRatio = totalRatio * scaledRatio
                    if (totalRatio > 4 * initRatio) {
                        totalRatio = 4 * initRatio
                    } else if (totalRatio < initRatio) {
                        totalRatio = initRatio
                    }
                    // 调用onDraw()方法绘制图片
                    invalidate()
                    lastFingerDis = fingerDis
                }
            }
            MotionEvent.ACTION_POINTER_UP -> if (event.pointerCount == 2) {
                // 手指离开屏幕时将临时值还原
                lastXMove = -1f
                lastYMove = -1f
            }
            MotionEvent.ACTION_UP -> {
                // 手指离开屏幕时将临时值还原
                lastXMove = -1f
                lastYMove = -1f
            }
            else -> {
            }
        }
        return true
    }

    /**
     * 根据currentStatus的值来决定对图片进行什么样的绘制操作。
     */
     override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        when (currentStatus) {
            STATUS_ZOOM_OUT, STATUS_ZOOM_IN -> zoom(canvas)
            STATUS_MOVE -> move(canvas)
            STATUS_INIT -> {
                initBitmap(canvas)
                sourceBitmap?.let { canvas.drawBitmap(it, mMatrix, null) }
            }
            else -> sourceBitmap?.let { canvas.drawBitmap(it, mMatrix, null) }
        }
    }

    /**
     * 对图片进行缩放处理。
     *
     * @param canvas
     */
    private fun zoom(canvas: Canvas) {
        mMatrix.reset()
        // 将图片按总缩放比例进行缩放
        mMatrix.postScale(totalRatio, totalRatio)
        val scaledWidth = sourceBitmap!!.width * totalRatio
        val scaledHeight = sourceBitmap!!.height * totalRatio
        var translateX = 0f
        var translateY = 0f
        // 如果当前图片宽度小于屏幕宽度，则按屏幕中心的横坐标进行水平缩放。否则按两指的中心点的横坐标进行水平缩放
        if (currentBitmapWidth < mWidth) {
            translateX = (mWidth - scaledWidth) / 2f
        } else {
            Build.VERSION.BASE_OS
            translateX = totalTranslateX * scaledRatio + centerPointX * (1 - scaledRatio)
            // 进行边界检查，保证图片缩放后在水平方向上不会偏移出屏幕
            if (translateX > 0) {
                translateX = 0f
            } else if (mWidth - translateX > scaledWidth) {
                translateX = mWidth - scaledWidth
            }
        }
        // 如果当前图片高度小于屏幕高度，则按屏幕中心的纵坐标进行垂直缩放。否则按两指的中心点的纵坐标进行垂直缩放
        if (currentBitmapHeight < mHeight) {
            translateY = (mHeight - scaledHeight) / 2f
        } else {
            translateY = totalTranslateY * scaledRatio + centerPointY * (1 - scaledRatio)
            // 进行边界检查，保证图片缩放后在垂直方向上不会偏移出屏幕
            if (translateY > 0) {
                translateY = 0f
            } else if (mHeight - translateY > scaledHeight) {
                translateY = mHeight - scaledHeight
            }
        }
        // 缩放后对图片进行偏移，以保证缩放后中心点位置不变
        mMatrix.postTranslate(translateX, translateY)
        totalTranslateX = translateX
        totalTranslateY = translateY
        currentBitmapWidth = scaledWidth
        currentBitmapHeight = scaledHeight
        canvas.drawBitmap(sourceBitmap!!, mMatrix, null)
    }

    /**
     * 对图片进行平移处理
     *
     * @param canvas
     */
    private fun move(canvas: Canvas) {
        mMatrix.reset()
        // 根据手指移动的距离计算出总偏移值
        val translateX = totalTranslateX + movedDistanceX
        val translateY = totalTranslateY + movedDistanceY
        // 先按照已有的缩放比例对图片进行缩放
        mMatrix.postScale(totalRatio, totalRatio)
        // 再根据移动距离进行偏移
        mMatrix.postTranslate(translateX, translateY)
        totalTranslateX = translateX
        totalTranslateY = translateY
        sourceBitmap?.let { canvas.drawBitmap(it, mMatrix, null) }
    }

    /**
     * 对图片进行初始化操作，包括让图片居中，以及当图片大于屏幕宽高时对图片进行压缩。
     *
     * @param canvas
     */
    private fun initBitmap(canvas: Canvas) {
        if (sourceBitmap != null) {
            mMatrix.reset()
            val bitmapWidth = sourceBitmap!!.width
            val bitmapHeight = sourceBitmap!!.height
            if (bitmapWidth > mWidth || bitmapHeight > mHeight) {
                if (bitmapWidth - mWidth > bitmapHeight - mHeight) {
                    // 当图片宽度大于屏幕宽度时，将图片等比例压缩，使它可以完全显示出来
                    val ratio = mWidth / (bitmapWidth * 1.0f)
                    mMatrix.postScale(ratio, ratio)
                    val translateY = (mHeight - bitmapHeight * ratio) / 2f
                    // 在纵坐标方向上进行偏移，以保证图片居中显示
                    mMatrix.postTranslate(0f, translateY)
                    totalTranslateY = translateY
                    initRatio = ratio
                    totalRatio = initRatio
                } else {
                    // 当图片高度大于屏幕高度时，将图片等比例压缩，使它可以完全显示出来
                    val ratio = mHeight / (bitmapHeight * 1.0f)
                    mMatrix.postScale(ratio, ratio)
                    val translateX = (mWidth - bitmapWidth * ratio) / 2f
                    // 在横坐标方向上进行偏移，以保证图片居中显示
                    mMatrix.postTranslate(translateX, 0f)
                    totalTranslateX = translateX
                    initRatio = ratio
                    totalRatio = initRatio
                }
                currentBitmapWidth = bitmapWidth * initRatio
                currentBitmapHeight = bitmapHeight * initRatio
            } else {
                // 当图片的宽高都小于屏幕宽高时，直接让图片居中显示
                val translateX = (mWidth - sourceBitmap!!.width) / 2f
                val translateY = (mHeight - sourceBitmap!!.height) / 2f
                mMatrix.postTranslate(translateX, translateY)
                totalTranslateX = translateX
                totalTranslateY = translateY
                initRatio = 1f
                totalRatio = initRatio
                currentBitmapWidth = bitmapWidth.toFloat()
                currentBitmapHeight = bitmapHeight.toFloat()
            }
            canvas.drawBitmap(sourceBitmap!!, mMatrix, null)
        }
    }

    /**
     * 计算两个手指之间的距离。
     *
     * @param event
     * @return 两个手指之间的距离
     */
    private fun distanceBetweenFingers(event: MotionEvent): Double {
        val disX = abs(event.getX(0) - event.getX(1))
        val disY = abs(event.getY(0) - event.getY(1))
        return sqrt(disX * disX + disY * disY.toDouble())
    }

    /**
     * 计算两个手指之间中心点的坐标。
     *
     * @param event
     */
    private fun centerPointBetweenFingers(event: MotionEvent) {
        val xPoint0 = event.getX(0)
        val yPoint0 = event.getY(0)
        val xPoint1 = event.getX(1)
        val yPoint1 = event.getY(1)
        centerPointX = (xPoint0 + xPoint1) / 2
        centerPointY = (yPoint0 + yPoint1) / 2
    }

    companion object {
        /**
         * 初始化状态常量
         */
        const val STATUS_INIT = 1

        /**
         * 图片放大状态常量
         */
        const val STATUS_ZOOM_OUT = 2

        /**
         * 图片缩小状态常量
         */
        const val STATUS_ZOOM_IN = 3

        /**
         * 图片拖动状态常量
         */
        const val STATUS_MOVE = 4
    }

    /**
     * ZoomImageView构造函数，将当前操作状态设为STATUS_INIT。
     *
     * @param context
     * @param attrs
     */
    init {
        currentStatus = STATUS_INIT
    }

    fun reset(){
        mMatrix.reset()
    }
}